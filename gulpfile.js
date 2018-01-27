var gulp           = require('gulp'),
		gutil          = require('gulp-util' ),
		sass           = require('gulp-sass'),
		browserSync    = require('browser-sync'),
		concat         = require('gulp-concat'),
		uglify         = require('gulp-uglify'),
		cleanCSS       = require('gulp-clean-css'),
		rename         = require('gulp-rename'),
		del            = require('del'),
		imagemin       = require('gulp-imagemin'),
		cache          = require('gulp-cache'),
		autoprefixer   = require('gulp-autoprefixer'),
		ftp            = require('vinyl-ftp'),
		notify         = require("gulp-notify"),
		rsync          = require('gulp-rsync');

// Создадим js объект в который пропишем все нужные нам пути, чтобы при необходимости легко в одном месте их редактировать:
var path = {
    dist: { //Тут мы укажем куда складывать готовые после сборки файлы
        root: 'dist/',
        js: 'dist/js/',
        css: 'dist/css/',
        img: 'dist/img/',
        fonts: 'dist/fonts/'
    },
    app: { //Пути откуда брать исходники
        html: 'app/**/*.html', //Синтаксис src/*.html говорит gulp что мы хотим взять все файлы с расширением .html
        js: 'app/js/',
        common: 'app/js/common.js',
        style: 'app/scss/**/*.scss',
        css: 'app/css/',
        img: 'app/img/**/*.*', //Синтаксис img/**/*.* означает - взять все файлы всех расширений из папки и из вложенных каталогов
        fonts: 'app/fonts/**/*.*'
    },
    watch: { //Тут мы укажем, за изменением каких файлов мы хотим наблюдать
        html: 'app/**/*.html',
        js: 'app/js/common.js',
		libs: 'app/libs/**/*.js',
        style: 'app/scss/**/*.scss'
    }
};

// Пользовательские скрипты проекта
gulp.task('common-js', function() {
	return gulp.src(path.app.common)
	.pipe(concat('common.min.js'))
	.pipe(uglify())
	.pipe(gulp.dest(path.app.js));
});

gulp.task('js', ['common-js'], function() {
	return gulp.src([
		'app/libs/jquery/dist/jquery.min.js',
		// 'app/libs/jquery/dist/jquery-ui.min.js',
		'app/js/common.min.js' // Всегда в конце
		])
	.pipe(concat('scripts.min.js'))
	.pipe(uglify()) // Минимизировать весь js (на выбор)
	.pipe(gulp.dest(path.app.js))
	.pipe(browserSync.reload({stream: true}));
});

gulp.task('sass', function() {
	return gulp.src(path.app.style)
	.pipe(sass({outputStyle: 'expand'}).on("error", notify.onError()))
	.pipe(rename({suffix: '.min', prefix : ''}))
	.pipe(autoprefixer(['last 15 versions']))
	.pipe(cleanCSS()) // Опционально, закомментировать при отладке
	.pipe(gulp.dest(path.app.css))
	.pipe(browserSync.reload({stream: true}));
});

gulp.task('watch', ['sass', 'js', 'browser-sync'], function() {
	gulp.watch(path.watch.style, ['sass']);
	gulp.watch([path.watch.libs, path.watch.js], ['js']);
	gulp.watch(path.watch.html, browserSync.reload);
});

gulp.task('imagemin', function() {
	return gulp.src(path.app.img)
	.pipe(cache(imagemin())) // Cache Images
	.pipe(gulp.dest(path.dist.img));
});

gulp.task('build', ['removedist', 'imagemin', 'sass', 'js'], function() {

	var buildFiles = gulp.src([
		'app/*.html',
		'app/.htaccess']).pipe(gulp.dest(path.dist.root));

	var buildCss = gulp.src([
		'app/css/main.min.css']).pipe(gulp.dest(path.dist.css));

	var buildJs = gulp.src([
		'app/js/scripts.min.js']).pipe(gulp.dest(path.dist.js));

	var buildFonts = gulp.src(path.app.fonts).pipe(gulp.dest(path.dist.fonts));

});

gulp.task('browser-sync', function() {
    browserSync({
        server: {
            baseDir: 'app'
        },
        notify: false,
        // tunnel: true,
        // tunnel: "projectmane", //Demonstration page: http://projectmane.localtunnel.me
        logPrefix: "Frontend_Devil"
    });
});

gulp.task('deploy', function() {

	var conn = ftp.create({
		host:      'hostname.com',
		user:      'username',
		password:  'userpassword',
		parallel:  10,
		log: gutil.log
	});

	var globs = [
	'dist/**',
	'dist/.htaccess'
	];
	return gulp.src(globs, {buffer: false})
	.pipe(conn.dest('/path/to/folder/on/server'));

});

gulp.task('rsync', function() {
	return gulp.src('dist/**')
	.pipe(rsync({
		root: 'dist/',
		hostname: 'username@yousite.com',
		destination: 'yousite/public_html/',
		// include: ['*.htaccess'], // Скрытые файлы, которые необходимо включить в деплой
		recursive: true,
		archive: true,
		silent: false,
		compress: true
	}));
});

gulp.task('removedist', function() { return del.sync('dist'); });
gulp.task('clearcache', function () { return cache.clearAll(); });

gulp.task('default', ['watch']);
