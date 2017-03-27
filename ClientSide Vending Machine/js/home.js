var clear = "", selection = 0, deposit = 0
var depository = {"quarter": 0, "dime": 0, "nickel": 0}

$(document).ready(function () {
    loadInventory()
    runVendingMachine()
})

function runVendingMachine() {
    $('.deposit').click(function (event) {
        depositMoney(this.id, this.value)
    })

    $('#vend').click(function (event) {
        vendItem()
    })

    $('#change-return').click(function (event) {
        returnChange(depository)
    })
}

function depositMoney(currency, value) {
    deposit = (parseFloat(deposit) + parseFloat(value)).toFixed(2)
    displayFund(deposit)
    displayChangeReturn(clear)
    updateDepository(currency)
}

function vendItem() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + deposit + '/item/' + selection,
        success: function (changeObj) {
            returnChange(changeObj)
            displayMessage("Thank You!!")
        },
        error: function (msg) {
            var error = msg.responseJSON.message
            displayMessage(error)
        }
    })
    loadInventory()
}

function returnChange(changeObj) {
    var change = ""
    $.each(changeObj, function (coin, count) {
        if (count > 0) change += count + " " + coin + "  "
    })
    displayChangeReturn(change)
    displayMessage(clear)
    displaySelection(clear)
    emptyDepository()
}


function updateDepository(currency) {
    switch (currency) {
        case "add-dollar":
            depository["quarter"] += 4
            break
        case "add-quarter":
            depository["quarter"] += 1
            break
        case "add-dime":
            depository["dime"] += 1
            break
        case "add-nickel":
            depository["nickel"] += 1
        default:
    }
}

function loadInventory() {
    clearInventory()
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (stock) {
            var inventory = $('#inventory')
            stockInventory(stock, inventory)
        },
        error: function () {
             displayMessage("No Items. Come Again!")
        }
    })
}

function stockInventory(stockArray, inventoryDiv) {
    $.each(stockArray, function (index, item) {
        var box = '<div onclick="selectItem(this)" class="col-sm-4 item">'   // don't couple javascript to html.  best practice: bind click listners in js
        box += '<p class="item-num">' + item.id + "</p>"
        box += '<p class="item-name">' + item.name + "</p>"
        box += '<p class="item-price">$' + item.price.toFixed(2) + "</p>"
        box += '<p class="item-quantity">Quantity Left: ' + item.quantity + "</p>"
        box += '</div>'

        inventoryDiv.append(box)
    })
}

function selectItem(item) {
    itemNum = $(item).children("p.item-num")[0].textContent
    itemName = $(item).children("p.item-name")[0].textContent

    displayMessage(itemName)
    displaySelection(itemNum)
    displayChangeReturn(clear)
    selection = itemNum
}

function emptyDepository() {
    deposit= 0
    displayFund(clear)
    $.each(depository, function(coin, count) {
        depository[coin] = 0
    })
}

function clearInventory() {
    $('#inventory').empty()
}

function displayFund(txt) {
    $('#fund').val(txt)
}
 
function displayMessage(txt) {
    $('#message').val(txt)
}

function displaySelection(txt) {
    selection = 0
    $('#selection').val(txt)
}

function displayChangeReturn(txt) {
    $('#change').val(txt)
}