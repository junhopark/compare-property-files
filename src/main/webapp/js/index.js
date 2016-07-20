Index = {
  showDateTime:function () {
    var $dateTime = $('#date-time');

    $.ajax({
      'url':'/current-date-time',
      'success':function (response) {
        $dateTime.hide();

        $dateTime.html(response);
        $dateTime.fadeIn(800);
      }
    });
  }
};

$(document).ready(function () {
  Index.showDateTime();

  $('#refresh-date-time').on('click', function (event) {
    event.preventDefault();
    Index.showDateTime();
  });
});