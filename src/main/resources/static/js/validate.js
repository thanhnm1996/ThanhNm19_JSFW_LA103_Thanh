// function Validator(options) {
//
//     let selectorRules = {};
//
//     function validate(inputElement, rule) {
//         let errorElement = inputElement.parentElement.querySelector(options.errorSelector);
//         let errorMesage;
//
//         let rules = selectorRules[rule.selector];
//         for (let i = 0; i < rules.length; i++) {
//             errorMesage = rules[i](inputElement.value);
//             if (errorMesage) break;
//         }
//         if (errorMesage) {
//             errorElement.innerText = errorMesage;
//             inputElement.parentElement.classList.add('invalid');
//         } else {
//             errorElement.innerText = '';
//             inputElement.parentElement.classList.remove('invalid');
//         }
//     }
//
//     const formElement = document.querySelector(options.form);
//     if (formElement) {
//         formElement.onsubmit = function (e) {
//             e.preventDefault();
//
//             options.rules.forEach(function (rule) {
//                 let inputElement = formElement.querySelector(rule.selector);
//                 validate(inputElement, rule);
//             });
//         }
//         options.rules.forEach(function (rule) {
//             if (Array.isArray(selectorRules[rule.selector])) {
//                 selectorRules[rule.selector].push(rule.test);
//             } else {
//                 selectorRules[rule.selector] = [rule.test];
//             }
//             let inputElement = formElement.querySelector(rule.selector);
//             if (inputElement) {
//                 inputElement.onblur = function () {
//                     validate(inputElement, rule);
//                 }
//                 inputElement.oninput = function () {
//                     let errorElement = inputElement.parentElement.querySelector('.form-message');
//                     errorElement.innerText = '';
//                     inputElement.parentElement.classList.remove('invalid');
//                 }
//             }
//         });
//     }
// }
//
// Validator.isRequired = function (selector, message) {
//     return {
//         selector: selector,
//         test: function (value) {
//             return value.trim() ? undefined : message || 'Please enter this field';
//         }
//     };
// }
//
// Validator.isEmail = function (selector, message) {
//     return {
//         selector: selector,
//         test: function (value) {
//             let regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//             return regex.test(value) ? undefined : message || 'Email is not valid';
//         }
//     };
// }
//
// Validator.minLength = function (selector, min, message) {
//     return {
//         selector: selector,
//         test: function (value) {
//             return value.length >= min ? undefined : message || `Please enter at least ${min} characters`;
//         }
//     };
// }
//
// Validator.maxLength = function (selector, max, message) {
//     return {
//         selector: selector,
//         test: function (value) {
//             return value.length <= max ? undefined : message || `Please enter at most ${max} characters`;
//         }
//     };
// }
//
// Validator.isConfirmed = function (selector, getConfirmValue, message) {
//     return {
//         selector: selector,
//         test: function (value) {
//             return value === getConfirmValue() ? undefined : message || 'Re-password is not valid';
//         }
//     };
// }
//
// Validator.isPhone = function (selector, message) {
//     return {
//         selector: selector,
//         test: function (value) {
//             let regex = /(03|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
//             return regex.test(value) ? undefined : message || 'Phone number is not valid';
//         }
//     };
// }
