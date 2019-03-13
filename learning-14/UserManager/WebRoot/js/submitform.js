function submitform(url,method,params){
	var form = document.createElement('form');
    form.action = url;
    form.method = method;
    

    for(var param in params){
    	var input = document.createElement('input');
        input.type = 'hidden';
        input.name = param;
        input.value = params[param];
        form.appendChild(input);
    }
    document.body.appendChild(form);
    console.log(form);
    form.submit();
    return form;
}
