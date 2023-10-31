let maxDate = new Date();
maxDate = maxDate.setMonth(maxDate.getMonth() + 3);

flatpickr('#fromCheckinDateToCheckoutDate', {
    mode: "range",
    locale: 'ja',
    minDate: 'today',
    maxDate: maxDate
});