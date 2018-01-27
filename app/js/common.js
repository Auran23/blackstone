$(function() {
    $('.dropdown').click(function (e) {
        $(this).next().toggleClass('show');
    });

    $('.menu-toggle').click(function () {
        $('.site-nav').toggleClass('site-nav-open');
        $(this).toggleClass('menu-toggle-open');
    });

    $('#search-trigger').click(function () {
        $('#search-input').toggleClass('search-input-open');
    });

    $(document).click(function (e) {
        if(!$(e.target).closest('.menu-toggle').length) {
            $('.site-nav').removeClass('site-nav-open');
            $(this).removeClass('menu-toggle-open')
        }

       if(!$(e.target).closest('.search-form').length) {
            $('#search-input').removeClass('search-input-open');
        }

        if(!$(e.target).closest('.dropdown').length) {
            $('.site-nav ul ul').removeClass('show');
        }
    });

    var mW = $(window).width();
    if(mW > 900) {
        $('.banner').on('mousemove', function (e) {
            var w = $(window).width();
            var h = $(window).height();
            var offsetX = 0.5 - e.pageX / w;
            var offsetY = 0.5 - e.pageY / h;

            $('.parallax').each(function (i, el) {
                var offset = parseInt($(el).data('offset'));
                var translate = 'translate3d(' + Math.round(offsetX*offset)
                    + 'px,' + Math.round(offsetY*offset) + 'px, 0';
                $(el).css({
                    'transform':translate
                });
            });
        });
    }
});

// $(window).resize(function() {
//     // console.log($(window).width());
//     // $('.search-input-open').css('width', $(window).width() + 'px')
// });
