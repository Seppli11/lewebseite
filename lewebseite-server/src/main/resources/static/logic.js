$(function () {
    function getCurrent(parent) {
        var activeEl = $(".active-img", parent)
        return Math.max($(".img-container", parent).index(activeEl), 0);
    }

    function set(parent, current, next) {
        var containers = $(".img-container", parent);
        var currentEl = $(containers[current]);
        var nextEl = $(containers[next]);
        currentEl.removeClass("active-img");
        nextEl.addClass("active-img");
    }

    function next(event, addIndex) {
        var parent = $(event.target).parent();
        var current = getCurrent(parent);
        var length = $(".img-container", parent).length;
        var next = (current + addIndex) % length;
        if (next < 0) next = length + next;
        set(parent, current, next);
    }

    $(".left-control").click(event => {
        next(event, -1);
    })
    $(".left-control *").click(event => {
        next(event, -1);
        return true;
    })
    $(".right-control").click(event => {
        next(event, 1);
    })
    $(".right-control *").click(event => {
        next(event, 1);
    })
})