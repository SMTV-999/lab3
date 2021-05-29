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
		if (xhr.readyState == 4) {
			if(xhr.status == 200){
				getList();
			} else {alert('AJAX request error');
			}
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
	
	if (table.value != "") {
		table.innerHTML = "";
	}
	
	data.forEach((element) => {
		let div = document.createElement('div');
		div.appendChild(document.createElement('hr'));
		div.appendChild(createName(element.login, element.id));
		div.appendChild(createFullName(element.fullName));
		div.appendChild(createCountposts(element.countposts));
		div.appendChild(createRemoveLink(element.id));
		div.appendChild(document.createElement('hr'));
		table.appendChild(div);
	});
	
}


function createName(name, id) {
	let td = document.createElement('a');
	td.innerText = name;
	td.href = "/users-editor/" + id;
	return td;
}

function createFullName(name) {
	let td = document.createElement('span');
	td.innerText =" | " + "ФИО: " + name + " | ";
	return td;
}

function createCountposts(name) {
	let td = document.createElement('span');
	td.innerText = "Количество постов: " + name + " | ";
	return td;
}

function createRemoveLink(name) {
	let td = document.createElement('button');
	td.innerText = "Удалить  ";
	td.onclick = function() {
		let str = "/ajax/remove/" + name;
		
		xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if(xhr.status == 200){
				getList();
			} else {alert('AJAX request error');
			}
		}
	}
		
		xhr.open("GET", str);
		xhr.send();
	};
	return td;
}