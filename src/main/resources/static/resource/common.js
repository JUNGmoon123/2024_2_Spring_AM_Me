$('select[data-value]').each(function(index, el) {
	const $el = $(el);

	defaultValue = $el.attr('data-value').trim();

	if (defaultValue.length > 0) {
		$el.val(defaultValue);
	}
});
//list에서 select의 data-value를 가져온다. form action=""에 PSOT/GET을 쓰지않고도 검색시 값을 유지할 수 있다.
// 예전에 function(form)했던거와 비슷.
// const $el 변수고 $(el)은 값이 되고  el.attr은 속성값을 가져와소 defaultValue로 전달해준다.