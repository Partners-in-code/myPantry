/**
 * 
 */

function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();

	if (password == "" && confirmPassword == "") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if (password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}

$(document).ready(function() {
					$("#txtConfirmPassword").keyup(checkPasswordMatch);
					$("#txtNewPassword").keyup(checkPasswordMatch);
					$('.delete-product').on('click', function (){
						/*<![CDATA[*/
					    var path = /*[[@{/}]]*/'remove';
					    /*]]>*/
						
						var id=$(this).attr('id');
						
						bootbox.confirm({
							message: "Are you sure to remove this product? It can't be undone.",
							buttons: {
								cancel: {
									label:'<i class="fa fa-times"></i> Cancel'
								},
								confirm: {
									label:'<i class="fa fa-check"></i> Confirm'
								}
							},
							callback: function(confirmed) {
								if(confirmed) {
									$.post(path, {'id':id}, function(res) {
										location.reload();
									});
								}
							}
						});
					});
				});