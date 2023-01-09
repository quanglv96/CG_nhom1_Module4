function validator(option, callback) {
    let rulesSelector = {};

    let form = document.querySelector(option.form);
    function validate(input, rule) {
        // lấy thẻ form group
        let errorElement = input.parentElement.querySelector(option.errorSelector)
        let errorMess;

        let rules = rulesSelector[rule.selector];
        for (let i = 0; i < rules.length; i++) {
            errorMess = rules[i](input.value)
            if (errorMess) break
        }
        if (errorMess) {
            errorElement.innerText = errorMess;
            input.parentElement.classList.add("invalid")
        } else {
            errorElement.innerText = ""
            input.parentElement.classList.remove("invalid")
        }
    }


    // check form khác null
    if (form) {
        form.onsubmit = function (e) {
            e.preventDefault();
            option.rules.forEach(function (rule) {
                let inputE = form.querySelector(rule.selector)
                validate(inputE, rule);
            })
            console.log(callback)
            callback();
        }

        option.rules.forEach(function (rule) {
            if (Array.isArray(rulesSelector[rule.selector])) {
                rulesSelector[rule.selector].push(rule.test);
            } else {
                rulesSelector[rule.selector] = [rule.test];
            }
            // lấy thẻ input cần validate
            let input = form.querySelector(rule.selector);
            if (input) {
                input.onblur = function () {
                    validate(input, rule);
                }
                input.oninput = function () {
                    let errorElement = input.parentElement.querySelector(option.errorSelector);
                    errorElement.innerText = ""
                    input.parentElement.classList.remove("invalid")
                }
            }
        })
    }
}

validator.isRequired = function(selector) {
    return {
        selector: selector,
        test: function (value) {
            return value.trim() ? undefined : "This field cannot be left blank";
        }
    }
}

validator.isConfirmed = function(selector, getConfirmValue) {
    return {
        selector: selector,
        test: function (value) {
            return (value === getConfirmValue()) ? undefined : "Re-password is not the same as password"
        }
    }
}

validator.lengthField = function(selector, min, max) {
    return {
        selector: selector,
        test: function (value) {
            return (value.length >= min && value.length <= max) ? undefined : "Password must be 6-8 character length"
        }
    }
}

validator.callback = function() {

}