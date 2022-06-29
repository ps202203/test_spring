/**
 * 공통 함수
 */
 
 function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $(this).val()=='' ){
			var item = $(this).attr('placeholder');
			alert(item + ' 입력하세요!');
			$(this).focus();
			ok = false;
			return ok;
		}
	});
	return ok;
}