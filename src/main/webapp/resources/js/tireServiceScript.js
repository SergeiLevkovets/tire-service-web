$(document).ready(function () {

    /**
     * service-item-price
     * */

    $('#').click(function () {
        if (isEmpty($('#width').val()) || isEmpty($('#height').val()) || isEmpty($('#diameter').val())) {
            alert("Поля «Размер колес» не могут быть пустыми");
            return;
        }
        let url = $(this).val();
        let dataString = 'width=' + $('#width').val() + '&height=' + $('#height').val() + '&diameter=' + $('#diameter').val();
        $.ajax({
            url: url,
            type: 'get',
            data: dataString,
        }).success(function () {
            alert("Данные сохранены")
        }).error(function () {
            alert("Данные не отправлены")
        })

    })

    /**
     * order-create
     * */

    $('.content-box-header').find(':checkbox').change(function () {
        if ($(this).find($('.input :input')).val > 0) {
            $(this).find($('.input')).addClass('has-success')
        } else {
            $(this).find($('.input')).removeClass('has-success')
        }
    })

    $('#tireSaveBtn').click(function () {
        if (isEmpty($('#width').val()) || isEmpty($('#height').val()) || isEmpty($('#diameter').val())) {
            alert("Поля «Размер колес» не могут быть пустыми");
            return;
        }
        let url = $(this).val();
        let dataString = 'width=' + $('#width').val() + '&height=' + $('#height').val() + '&diameter=' + $('#diameter').val();
        $.ajax({
            url: url,
            type: 'get',
            data: dataString,
        }).success(function () {
            alert("Данные сохранены")
        }).error(function () {
            alert("Данные не отправлены")
        })

    })


    $('#complex').click(function () {
        $('#mounting').prop('checked', 'true');
        $('#wheelRemove').prop('checked', 'true');
        $('#balancing').prop('checked', 'true');
    })

    $('#valveReplacement').click(function () {
        if ($('#valveReplacement').prop('checked')) {
            $('#valveCount').prop('required', 'true')
        } else {
            $('#valveCount').prop('required', null)
            $('#valveCount').val('')
            $('#valve').val('')
        }
    })

    $('#sealing').click(function () {
        if ($('#sealing').prop('checked')) {
            $('#sealingCount').prop('required', 'true')
        } else {
            $('#sealingCount').prop('required', null)
            $('#sealingCount').val('')
        }
    })

    $('#repair input').click(function () {
        if ($('#diagnostic').prop('checked')
            || $('#punctureRepair').prop('checked')
            || $('#cutRepair').prop('checked')
            || $('#bigCutRepair').prop('checked')
            || $('#verticalCutRepair').prop('checked')
            || $('#diagnostic').prop('checked')) {
            $('#repairCount').prop('required', 'true')
        } else {
            $('#repairCount').prop('required', null)
            $('#repairCount').val('')
            $('#patch').val('')
        }
    })

    $('#submit_order').click(function () {
        if (isEmpty($('#wheelCount').val())) {
            alert("Поле «Количество колес» не может быть пустым");
            return;
        }
        if (isEmpty($('#width').val()) || isEmpty($('#height').val()) || isEmpty($('#diameter').val())) {
            alert("Поля «Размер колес» не могут быть пустыми");
            return;
        }
        if ($('#valveReplacement').prop('checked')) {
            if (isEmpty($('#valveCount').val())) {
                alert("Поле «Количество вентелей» не может быть пустым");
                return;
            }
        }
        if ($('#sealing').prop('checked')) {
            if (isEmpty($('#sealingCount').val())) {
                alert("Поле «Количество колес для гермитизации» не может быть пустым");
                return;
            }
        }
        if ($('#diagnostic').prop('checked')
            || $('#punctureRepair').prop('checked')
            || $('#cutRepair').prop('checked')
            || $('#bigCutRepair').prop('checked')
            || $('#verticalCutRepair').prop('checked')
            || $('#diagnostic').prop('checked')) {

            if (isEmpty($('#repairCount').val())) {
                alert("Поле «Количество колес для ремонта» не может быть пустым");
                return;
            }
        }

        $('#order').submit();

        /*let url = $('#order').attr('action');
        let param = $('#order').serialize();
        $.ajax({
            url: url,
            type: 'POST',
            data: param,
        }).success(function () {
            alert("Данные формы сохранены")
        }).error(function () {
            alert("Данные формы не отправлены")
        })*/
    })

    /**
     * registration
     * */

    $('#submit_registration').click(function () {
        if (isEmpty($('#email').val()) || !(($('#email').val().match(/.+?\@.+/g) || []).length === 1)) {
            alert(" Заполните правильно поле «Email»");
            return;
        }
        if (isEmpty($('#phone').val()) || !(/^[+]?\d[\d\(\)\ -]{4,14}\d$/.test(($('#phone').val())))) {
            alert(" Заполните правильно поле «Phone»");
            return;
        }
        if (isEmpty($('#password').val())) {
            alert("Поле «Password» не может быть пустым");
            return;
        }
        if ($('#password').val() !== $('#confirm_password').val()) {
            alert("Поля «Password» и «Confirm Password» должны совпадать");
            return;
        }
        $('#registration_form').submit();
    });

    /**
     *login
     * */

    $('#submit_login').click(function () {
        if (isEmpty($('#email').val()) & isEmpty($('#phone').val())) {
            alert(" Заполните поле «Email» или «Phone»");
            return;
        }
        if (isNotEmpty($('#email').val())) {
            if (!(($('#email').val().match(/.+?\@.+/g) || []).length === 1)) {
                alert(" Заполните правильно поле «Email»");
                return;
            }
        }
        if (isNotEmpty($('#phone').val())) {
            if (!(/^[+]?\d[\d\(\)\ -]{4,14}\d$/.test(($('#phone').val())))) {
                alert(" Заполните правильно поле «Phone»");
                return;
            }
        }
        if (isEmpty($('#password').val())) {
            alert("Поле «Password» не может быть пустым");
            return;
        }
        $('#login_form').submit();
    })

    /**
     * profile
     */

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
    if (value == null) {
        return true;
    }
    return value.trim() === '';
}

function isNotEmpty(value) {
    return !isEmpty(value);
}

