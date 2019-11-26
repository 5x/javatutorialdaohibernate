function cityModalHandle(event) {
    var button = $(event.relatedTarget);
    var cityId = button.data('city_id');
    var cityName = button.data('city_name');

    var modal = $(this);
    modal.find('.city__id').text(cityId);
    modal.find('.city__name').text(cityName);
    modal.find('input[name="city_id"]').val(cityId);

    var cityNameInput = modal.find('input[name="city-name"]');
    if (cityNameInput && cityNameInput.length) {
        cityNameInput.val(cityName);
    }
}

$('#delete-city-modal').on('show.bs.modal', cityModalHandle);
$('#update-city-modal').on('show.bs.modal', cityModalHandle);

function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));

    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function setCookie(name, value) {
    var cookieLifetime = 24 * 60 * 60 * 1000;
    var expires = new Date();

    expires.setTime(expires.getTime() + cookieLifetime);
    document.cookie = name + '=' + value + ';expires=' + expires.toUTCString();
}

var COOKIE_SELECTED_CITY_ITEMS_KEY = 'CITY_ITEMS';
$('.city-list :checkbox').change(function () {
    var cityId = $(this).data('city-id') + '';
    var items = getCookie(COOKIE_SELECTED_CITY_ITEMS_KEY);
    var cookieValue;
    var index;

    items = items ? items.split('+') : [];
    index = items.indexOf(cityId);

    if (this.checked && index === -1) {
        items.push(cityId);
    }
    if (!this.checked && index !== -1) {
        items.splice(index, 1);
    }

    cookieValue = items.join('+');
    setCookie(COOKIE_SELECTED_CITY_ITEMS_KEY, cookieValue);
});
