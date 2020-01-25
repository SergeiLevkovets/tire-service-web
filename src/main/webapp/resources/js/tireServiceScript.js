function fail() {
    let url = $('#order_form').attr('action');
    let param = $('#order_form').serialize();
    $.ajax({
        url: url,
        type: 'get',
        data: param,
    }).success(function (response) {
        let val = response.toString();
        let html = $(document).html();
        $(document).html(val.toString())
    }).error(function () {
        alert("Данные не отправлены")
    })
}

function changeClass() {
    let inputs = $('.observable [ type = number]');
    inputs.each(function () {
        if ($(this).val() > 0) {
            if (!$(this).parent().parent().is('.has-success')) {
                $(this).parent().parent().addClass('has-success')
            }
        } else {
            if ($(this).parent().parent().is('.has-success')) {
                $(this).parent().parent().removeClass('has-success')
            }
        }
    });
};

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



    $('.observable [ type = number]').on('input', function () {
        if ($(this).val() > 0) {
            if (!$(this).parent().parent().is('.has-success')) {
                $(this).parent().parent().addClass('has-success')
            }
        } else {
            if ($(this).parent().parent().is('.has-success')) {
                $(this).parent().parent().removeClass('has-success')
            }
        }
    });

    $('.observable :button').click(function () {
        let elem = $(this).parent().parent().next();
        let value = elem.find('[type = number]').val();
        if (value > 0) {
            if (!elem.is('.has-success')) {
                elem.addClass('has-success')
            }
        } else {
            if (elem.is('.has-success')) {
                elem.removeClass('has-success')
            }
        }
    });

    $('#complex').click(function () {
        if (isEmpty($('#wheelCount').val())) {
            alert("Поле «Количество колес» не может быть пустым");
            return;
        }
        $('#mounting').val($('#wheelCount').val());
        $('#dismantling').val($('#wheelCount').val());
        $('#wheelRemove').val($('#wheelCount').val());
        $('#wheelInstall').val($('#wheelCount').val());
        $('#balancing').val($('#wheelCount').val());

        changeClass();
    });


    $('#tireSaveBtn').click(function () {
        if (isEmpty($('#width').val()) || isEmpty($('#height').val()) || isEmpty($('#diameter').val())) {
            alert("Поля «Размер колес» не могут быть пустыми");
            return;
        }
        $(document)
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

    });

    $('#submit_order').click(function () {
        if (isEmpty($('#wheelCount').val())) {
            alert("Поле «Количество колес» не может быть пустым");
            return;
        }
        if (isEmpty($('#width').val()) || isEmpty($('#height').val()) || isEmpty($('#diameter').val())) {
            alert("Поля «Размер колес» не могут быть пустыми");
            return;
        }

        $('#order_form').submit();

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
    });

    changeClass();

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

