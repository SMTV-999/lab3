/**
 * 
 */

let xhr = new XMLHttpRequest();
getList();

function addUser(login, fullname) {
	let name = document.getElementById(login).value;
	let fname = document.getElementById(fullname).value;
	
	xhr.open('POST', '/ajax/adduser');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status != 200) {
			alert('AJAX request error');
		}
	}
	
	xhr.send("login=" + name + "&fullName=" + fname);
}


function getList() {
	xhr.onreadystatechange=function()
  {
  if (xhr.readyState==4 && xhr.status==200)
    {
    var data = JSON.parse(xhr.responseText);
	fillTable(data);
	console.log(data);
    }
  }
xhr.open("GET","ajax/getusers");
xhr.send();
}

function fillTable(data) {
	
	let table = document.getElementById('usersList');
	
	data.forEach((element) => {
		let div = document.createElement('div');
		div.appendChild(document.createElement('hr'));
		div.appendChild(createName(element.login));
		div.appendChild(createFullName(element.fullName));
		div.appendChild(createCountposts(element.countposts));
		div.appendChild(createRemoveLink(element.id));
		div.appendChild(createOpenLink(element.id));
		div.appendChild(document.createElement('hr'));
		table.appendChild(div);
	});
	
}


function createName(name) {
	let td = document.createElement('span');
	td.innerText = "Логин: " + name + " | ";
	return td;
}

function createFullName(name) {
	let td = document.createElement('span');
	td.innerText = "ФИО: " + name + " | ";
	return td;
}

function createCountposts(name) {
	let td = document.createElement('span');
	td.innerText = "Количество постов: " + name + " | ";
	return td;
}

function createRemoveLink(name) {
	let td = document.createElement('a');
	td.innerText = "Удалить  ";
	td.href = "/users-editor/" + name + "/remove";
	return td;
}

function createOpenLink(name) {
	let td = document.createElement('a');
	td.innerText = "Открыть  ";
	td.href = "/users-editor/" + name;
	return td;
}