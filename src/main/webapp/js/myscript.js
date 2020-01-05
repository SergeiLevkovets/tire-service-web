$(document).ready(function () {

    $('#wheel_size_count').click(function () {
        let elem = $('#wheel_size')
        $('#wheel_size_btn').before('<div class="form-group row">' + elem.html() + '</div>')
    })

    $('#valve_replacement').click(function () {
        if ($('#valve_replacement').prop('checked')) {
            $('#valve_count').prop('required', 'true')
        } else {
            $('#valve_count').prop('required', null)
            $('#valve_count').val('')
            $('#valve_type').val('')
        }
    })

    $('#sealing').click(function () {
        if ($('#sealing').prop('checked')) {
            $('#sealing_count').prop('required', 'true')
        } else {
            $('#sealing_count').prop('required', null)
            $('#sealing_count').val('')
        }
    })

    $('#repair input').click(function () {
        if ($('#puncture_repair').prop('checked')
            || $('#cut_repair').prop('checked')
            || $('#big_cut_repair').prop('checked')
            || $('#vertical_cut_repair').prop('checked')
            || $('#repair_diagnostic').prop('checked')) {
            $('#repair_count').prop('required', 'true')
        } else {
            $('#repair_count').prop('required', null)
            $('#repair_count').val('')
            $('#patch_type').val('')
        }
    })

    $('#submit_order').click(function () {
        if (isEmpty($('#wheel_count').val())) {
            alert("Поле «Количество колес» не может быть пустым");
            return;
        }


        if (isEmpty($('#email_reg').val()) || !(($('#email_reg').val().match(/.+?\@.+/g) || []).length === 1)) {
            alert(" Заполните правильно поле «Email»");
            return;
        }
        if (isEmpty($('#phone_reg').val()) || !(/^[+]?\d[\d\(\)\ -]{4,14}\d$/.test(($('#phone_reg').val())))) {
            alert(" Заполните правильно поле «Phone»");
            return;
        }

        if ($('#password_reg').val() !== $('#confirm_password_reg').val()) {
            alert("Поля «Password» и «Confirm Password» должны совпадать");
            return;
        }
        $('#registration_form').submit();
    })


    $('#submit_reg').click(function () {
        if (isEmpty($('#email_reg').val()) || !(($('#email_reg').val().match(/.+?\@.+/g) || []).length === 1)) {
            alert(" Заполните правильно поле «Email»");
            return;
        }
        if (isEmpty($('#phone_reg').val()) || !(/^[+]?\d[\d\(\)\ -]{4,14}\d$/.test(($('#phone_reg').val())))) {
            alert(" Заполните правильно поле «Phone»");
            return;
        }
        if (isEmpty($('#password_reg').val())) {
            alert("Поле «Password» не может быть пустым");
            return;
        }
        if ($('#password_reg').val() !== $('#confirm_password_reg').val()) {
            alert("Поля «Password» и «Confirm Password» должны совпадать");
            return;
        }
        $('#registration_form').submit();
    })

    $('#submit_login').click(function () {
        if (isEmpty($('#email_login').val()) || !(($('#email_login').val().match(/.+?\@.+/g) || []).length === 1)) {
            alert(" Заполните правильно поле «Email»");
            return;
        }
        if (isEmpty($('#password_login').val())) {
            alert("Поле «Password» не может быть пустым");
            return;
        }
        $('#login_form').submit();
    })

    $('#submit_forgot').click(function () {
        if (isEmpty($('#email_forgot').val()) || !(($('#email_forgot').val().match(/.+?\@.+/g) || []).length === 1)) {
            alert(" Заполните правильно поле «Email»");
            return;
        }
        $('#forgot_form').submit();
    })

    $('#submit_storage').click(function () {
        if (isEmpty($('#tire_name').val())) {
            alert("Поле «Производитель и модель» не может быть пустым");
            return;
        }
        if ($('#tire_name').val().match(/^[0-9@_-]{3,50}$/u)) {
            alert("Поле «Производитель и модель» не может состоять только из цифр");
            return;
        }
        if (isEmpty($('#width_tire').val())) {
            alert("Поле «Ширина колеса» не может быть пустым");
            return;
        }
        if (isEmpty($('#height_tire').val())) {
            alert("Поле «Высота профиля» не может быть пустым");
            return;
        }
        if (isEmpty($('#diameter_wheel').val())) {
            alert("Поле «Диаметр диска» не может быть пустым");
            return;
        }
        if (isEmpty($('#placement_date').val())) {
            alert("Поле «Дата размещения» не может быть пустым");
            return;
        }
        if (isEmpty($('#end_date').val())) {
            alert("Поле «Дата окончания хранения» не может быть пустым");
            return;
        }
        $('#storage_form').submit();
    })

    $('#profile_submit').click(function () {
        if (isEmpty($('#profile_name ').val())) {
            alert("Поле «Name» не может быть пустым");
            return;
        }
        if (isEmpty($('#profile_email').val()) || !(($('#profile_email').val().match(/.+?\@.+/g) || []).length === 1)) {
            alert(" Заполните правильно поле «Email»");
            return;
        }
        if (isEmpty($('#profile_phone').val()) || !(/^[+]?\d[\d\(\)\ -]{4,14}\d$/.test(($('#profile_phone').val())))) {
            alert(" Заполните правильно поле «Phone»");
            return;
        }
        if (isEmpty($('#profile_password').val())) {
            alert("Поле «Password» не может быть пустым");
            return;
        }

        $('#profile_form').submit();
    })

});


function isEmpty(value) {
    return value.trim() === '';
}

