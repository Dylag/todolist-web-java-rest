let userInput  = document.getElementById("userInput")
let table = document.getElementById('table')
let startDatePicker = document.getElementById("startDatePicker")
let endDatePicker = document.getElementById("endDatePicker")



function addToList(){

    let newTodo = {
        txt:userInput.value,
        startdate: startDatePicker.value,
        enddate: endDatePicker.value
    }

    fetch("http://localhost:8080/todo", {
        method: "POST",
        body: JSON.stringify(newTodo),
        headers: {
            "Content-type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => {
            newTodo.id = json.id
            let newRow = document.createElement('tr')

            let newIdCell = document.createElement('td')
            newIdCell.textContent = newTodo.id
            newIdCell.className = "idColumn"

            let newTxtCell = document.createElement('td')
            newTxtCell.textContent = newTodo.txt
            newTxtCell.className ="txtColumn"

            let newStartDateCell= document.createElement('td')
            newStartDateCell.textContent = newTodo.startdate
            newStartDateCell.className = "startDateColumn"

            let newEndDateCell = document.createElement('td')
            newEndDateCell.textContent = newTodo.enddate
            newEndDateCell.className = "endDateColumn"

            let newDeleteBtnCell = document.createElement('td')
            newDeleteBtnCell.className = "deleteBtnColumn"
            let newDeleteBtn = document.createElement('button')
            newDeleteBtn.textContent = "---"
            newDeleteBtn.onclick = (()=>removeElement(newRow,newTodo.id))
            newDeleteBtnCell.appendChild(newDeleteBtn)

            let newShareBtnCell = document.createElement("td")
            newShareBtnCell.className = "shareBtnColumn"
            let newShareBtn = document.createElement('button')
            newShareBtn.textContent = "share with..."
            newShareBtn.onclick = (() => shareTodo(newTodo.id))
            newShareBtnCell.appendChild(newShareBtn)


            newRow.appendChild(newIdCell)
            newRow.appendChild(newTxtCell)
            newRow.appendChild(newStartDateCell)
            newRow.appendChild(newEndDateCell)
            newRow.appendChild(newDeleteBtnCell)
            newRow.appendChild(newShareBtnCell)

            table.appendChild(newRow)

        })
}

function removeElement(element,todoId){
    fetch("http://localhost:8080/todo?" + new URLSearchParams({
        'todoId':todoId
    }),{
        method:"DELETE"
    })
        .then(r => r.json())
        .then(response => {
            if(response.text==='ok')
                element.remove()
            else
                alert(response.text)
        })
}

function shareTodo(todoId){
    fetch("http://localhost:8080/todo?"+ new URLSearchParams({
        "todoId":todoId,
        "recipient":prompt('enter recipient username')
    }),{
        method:"PATCH"
    }).then(r => r.json())
        .then(r => alert(r.text))
}