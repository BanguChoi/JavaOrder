$(document).ready(function() {
    // Enable the editing mode for a field
    function enableEditing(fieldId) {
        const inputField = $(`#${fieldId}`);
        const changeButton = inputField.siblings('button.btn-success');
        const editButton = inputField.siblings('button.btn-primary');
        const searchButton = inputField.siblings('button.btn-secondary');
        
        inputField.prop('readonly', false);
        changeButton.show();
        editButton.hide();
        if (fieldId === 'memberAddress') {
            searchButton.show();
        }
    }

    // Disable the editing mode for a field
    function disableEditing(fieldId) {
        const inputField = $(`#${fieldId}`);
        const changeButton = inputField.siblings('button.btn-success');
        const editButton = inputField.siblings('button.btn-primary');
        const searchButton = inputField.siblings('button.btn-secondary');
        
        inputField.prop('readonly', true);
        changeButton.hide();
        editButton.show();
        if (fieldId === 'memberAddress') {
            searchButton.hide();
        }
    }

    // Update the field value via AJAX
    function updateField(fieldId) {
        const inputField = $(`#${fieldId}`);
        const newValue = inputField.val();
        const memberCode = $('#memberCode').val();
        
        $.ajax({
            url: '/javaOrder/member/updateField',
            method: 'POST',
            data: {
                memberCode: memberCode,
                fieldId: fieldId,
                newValue: newValue
            },
            success: function(response) {
                // On successful update
                disableEditing(fieldId);
                alert('변경이 완료되었습니다.');
            },
            error: function() {
                // On error
                alert('변경 중 오류가 발생했습니다.');
            }
        });
    }

    // Address search with Daum API
    function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                const fullAddress = data.address;
                const extraAddress = data.bname ? (data.bname + (data.buildingName ? ', ' + data.buildingName : '')) : '';
                const address = extraAddress ? fullAddress + ' (' + extraAddress + ')' : fullAddress;
                $('#memberAddress').val(address);
            }
        }).open();
    }

    // Bind events to buttons
    $('button.btn-primary').on('click', function() {
        const fieldId = $(this).siblings('input').attr('id');
        enableEditing(fieldId);
    });

    $('button.btn-success').on('click', function() {
        const fieldId = $(this).siblings('input').attr('id');
        updateField(fieldId);
    });

    $('#changePasswordBtn').on('click', function() {
        window.location.href = '/javaOrder/member/changePassword';
    });

    $('#searchAddressBtn').on('click', function() {
        searchAddress();
    });
});
