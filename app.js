function hideAllPages(){
    document.getElementById("login-page").style.display = "none";
    document.getElementById("member-page").style.display = "none";
    document.getElementById("librarian-page").style.display = "none";
    document.getElementById("register-page").style.display = "none";
    document.getElementById("return-page").style.display = "none";
    document.getElementById("add-book-page").style.display = "none";
    document.getElementById("remove-book-page").style.display = "none";
    document.getElementById("book-details-page").style.display = "none";
    document.getElementById("my-books-page").style.display = "none";
    document.getElementById("edit-profile-page").style.display = "none";
    document.getElementById("member-history-page").style.display = "none";
    document.getElementById("book-borrowers-page").style.display = "none";
}

function showLoginPage(){
    hideAllPages();
    document.getElementById("navbar").style.display = "none";
    document.getElementById("login-page").style.display = "flex";
    document.getElementById("id-input").value = "";
}

document.getElementById("register-button").addEventListener("click", function(){
    hideAllPages();
    document.getElementById("register-page").style.display = "block";
});

let members = [];

document.getElementById("register-submit").addEventListener("click", function(){
    let name = document.getElementById("reg-name").value;
    let email = document.getElementById("reg-email").value;
    let phone = document.getElementById("reg-phone").value;

    if(!name || !email || !phone){
        alert("Please fill in all fields");
        return;
    }

    let membershipNumber = "1" + Math.floor(Math.random() * 1000000000);

    let newMember = { name: name, email: email, phone: phone, membershipNumber: membershipNumber };
    members.push(newMember);

    alert("Registered Successfully!\nYour Membership Number: " + membershipNumber);
    showLoginPage();
});

document.getElementById("register-back").addEventListener("click", function(){
    showLoginPage();
});

let currentMember = null;

document.getElementById("login-button").addEventListener("click", function(){
    let id = document.getElementById("id-input").value;

    if(id.startsWith("9")){
        showLibrarianPage();
    }
    else if(id.startsWith("1")){
        let foundMember = members.find(m => m.membershipNumber === id);

        if(foundMember){
            currentMember = foundMember;
            showMemberPage();
        }
        else{
            alert("Member not found. Please register first.");
        }
    }
    else{
        alert("Invalid ID");
    }
});

function showMemberPage(){
    hideAllPages();
    showMemberNavbar();
    document.getElementById("member-page").style.display = "block";
    document.getElementById("search-input").value = "";
    renderBooks(books);
}

function showMemberNavbar(){
    let navbar = document.getElementById("navbar");
    navbar.style.display = "flex";
    document.getElementById("navbar-buttons").innerHTML = `
        <button onclick="showMemberPage()">Browse Books</button>
        <button onclick="showMyBooks()">My Books</button>
        <button onclick="showReturnPage()">Return Book</button>
        <button onclick="showEditProfile()">Edit Profile</button>
        <button onclick="showLoginPage()">Logout</button>
    `;
}

function renderBooks(bookArray){
    let list = document.getElementById("book-list");
    list.innerHTML = "";

    bookArray.forEach(function(book){
        let li = document.createElement("li");
        li.textContent = book.title + " -  " + book.author;

        li.addEventListener("click", function(){
            selectedBook = book;

            let availableCopies = bookCopies.filter(c => c.bookId === book.id && c.isAvailable).length;
            let totalCopies = bookCopies.filter(c => c.bookId === book.id).length;

            document.getElementById("detail-title").textContent = "Title: " + book.title;
            document.getElementById("detail-author").textContent = "Author: " + book.author;
            document.getElementById("detail-publisher").textContent = "Publisher: " + book.publisher;
            document.getElementById("detail-isbn").textContent = "ISBN: " + book.isbn;
            document.getElementById("detail-description").textContent = "<strong>Description:</strong> " + (book.description || "No description available");
            document.getElementById("detail-copies").textContent = "<strong>Available Copies:</strong> " + availableCopies + " / " + totalCopies;
            
            hideAllPages();
            showMemberNavbar();
            document.getElementById("book-details-page").style.display = "block"
        });

        list.appendChild(li);
    });
}

document.getElementById("details-back").addEventListener("click", function(){
    showMemberPage();
});

document.getElementById("search-input").addEventListener("input", function(){
    let keyword = this.value.toLowerCase();

    let filtered = books.filter(function(book){
        return book.title.toLowerCase().includes(keyword) || book.author.toLowerCase().includes(keyword);
    });

    renderBooks(filtered);
});

let bookCopies =[];
let books = [];
let borrows = [];
let selectedBook = null;
let selectedBorrow = null;

document.getElementById("borrow-button").addEventListener("click", function(){
    let copy = bookCopies.find(c => c.bookId === selectedBook.id && c.isAvailable);

    if(!copy){
        alert("No available copies to borrow");
        return;
    }

    copy.isAvailable = false;

    let dueDate = new Date();
    dueDate.setDate(dueDate.getDate() + 14);

    borrows.push({ bookCopy: copy, member: currentMember, dueDate: dueDate, returned: false});

    alert("Book Borrowed Successfully! Due Date: " + dueDate.toDateString());
});

document.getElementById("return-back").addEventListener("click", function(){
    showMemberPage();
});

document.getElementById("return-submit").addEventListener("click", function(){
    if(selectedBorrow === null){
        alert("Select a book to return");
        return;
    }

    selectedBorrow.returned = true;
    selectedBorrow.bookCopy.isAvailable = true;
    selectedBorrow = null;

    alert("Book Returned Successfully!");
    showMemberPage();
});

function showReturnPage(){
    hideAllPages();
    showMemberNavbar();
    document.getElementById("return-page").style.display = "block";

    let list = document.getElementById("borrow-list");
    list.innerHTML = "";

    let myBorrows = borrows.filter(function(b){
        return b.member.membershipNumber === currentMember.membershipNumber && !b.returned;
    });

    if(myBorrows.length === 0){
        list.innerHTML = "<li>You have no books to return.</li>";
        return;
    }

    myBorrows.forEach(function(borrow){
        let li = document.createElement("li");
        let book = books.find(b => b.id === borrow.bookCopy.bookId);
        li.textContent = book.title + " | Due: " + borrow.dueDate.toDateString();

        li.addEventListener("click", function(){
            selectedBorrow = borrow;
            document.querySelectorAll("#borrow-list li").forEach(l => l.style.background = "");
            li.style.background = "#d8f3dc";
        });

        list.appendChild(li);
    });
}

function showMyBooks(){
    hideAllPages();
    showMemberNavbar();
    document.getElementById("my-books-page").style.display = "block";

    let list = document.getElementById("my-books-list");
    list.innerHTML = "";

    let myBorrows = borrows.filter(b => b.member.membershipNumber === currentMember.membershipNumber);

    if(myBorrows.length === 0){
        list.innerHTML = "<p>You have no borrowed books.</p>";
        return;
    }

    myBorrows.forEach(function(borrow){
        let li = document.createElement("li");
        li.textContent = "Book ID: " + borrow.bookCopy.bookId + 
            " | Due: " + borrow.dueDate.toDateString() + 
            " | Status: " + (borrow.returned ? "Returned" : "Active");
        list.appendChild(li);
    });
}

function showEditProfile(){
    hideAllPages();
    showMemberNavbar();
    document.getElementById("edit-profile-page").style.display = "block";
    document.getElementById("edit-name").value = currentMember.name;
    document.getElementById("edit-email").value = currentMember.email;
    document.getElementById("edit-phone").value = currentMember.phone;
}

document.getElementById("edit-submit").addEventListener("click", function(){
    currentMember.name = document.getElementById("edit-name").value;
    currentMember.email = document.getElementById("edit-email").value;
    currentMember.phone = document.getElementById("edit-phone").value;
    alert("Profile Updated Successfully!");
    showMemberPage();
});

document.getElementById("edit-back").addEventListener("click", function(){
    showMemberPage();
});

document.getElementById("my-books-back").addEventListener("click", function(){
    showMemberPage();
});

function showLibrarianPage(){
    hideAllPages();
    showLibrarianNavbar();
    document.getElementById("librarian-page").style.display = "block";
    document.getElementById("list-search").value = "none";
    document.getElementById("list-search").value = "";
    document.getElementById("output-list").innerHTML = "";
}

function showLibrarianNavbar(){
    let navbar = document.getElementById("navbar");
    navbar.style.display = "flex";
    document.getElementById("navbar-buttons").innerHTML = `
        <button onclick="showLibrarianPage()">Dashboard</button>
        <button onclick="showLoginPage()">Logout</button>
    `;
}


document.getElementById("list-books-button").addEventListener("click", function(){
    let output = document.getElementById("output-list");
    let search = document.getElementById("list-search");
    search.style.display = "block";
    search.placeholder = "Search books...";
    search.value = "";
    search.oninput = function(){
        let keyword = this.value.toLowerCase();
        output.innerHTML = "";
        books.filter(b =>
            b.title.toLowerCase().includes(keyword) ||
            b.author.toLowerCase().includes(keyword) ||
            b.isbn.toLowerCase().includes(keyword)
        ).forEach(function(book){
            let li = document.createElement("li");
            li.innerHTML =
                "<strong>Title:</strong> " + book.title + " &nbsp;|&nbsp; " +
                "<strong>Author:</strong> " + book.author + " &nbsp;|&nbsp; " +
                "<strong>ISBN:</strong> " + book.isbn + " &nbsp;|&nbsp; " +
                "<strong>Copies:</strong> " + bookCopies.filter(c => c.bookId === book.id).length;
            li.addEventListener("click", function(){
                showBookBorrowers(book);
            });
            output.appendChild(li);
        });
    };

    output.innerHTML = "";
    books.forEach(function(book){
        let li = document.createElement("li");
        li.innerHTML =
            "<strong>Title:</strong> " + book.title + " &nbsp;|&nbsp; " +
            "<strong>Author:</strong> " + book.author + " &nbsp;|&nbsp; " +
            "<strong>ISBN:</strong> " + book.isbn + " &nbsp;|&nbsp; " +
            "<strong>Copies:</strong> " + bookCopies.filter(c => c.bookId === book.id).length;
        li.addEventListener("click", function(){
            showBookBorrowers(book);
        });
        output.appendChild(li);
    });
});

document.getElementById("borrowed-list-button").addEventListener("click", function(){
    let output = document.getElementById("output-list");
    let search = document.getElementById("list-search");
    search.style.display = "block";
    search.placeholder = "Search borrowed books...";
    search.value = "";
    search.oninput = function(){
        let keyword = this.value.toLowerCase();
        output.innerHTML = "";
        borrows.filter(b => !b.returned).forEach(function(borrow){
            let book = books.find(b => b.id === borrow.bookCopy.bookId);
            if(book.title.toLowerCase().includes(keyword) ||
               borrow.member.name.toLowerCase().includes(keyword)){
                let li = document.createElement("li");
                li.innerHTML =
                    "<strong>Book:</strong> " + book.title + " &nbsp;|&nbsp; " +
                    "<strong>Member:</strong> " + borrow.member.name + " &nbsp;|&nbsp; " +
                    "<strong>ID:</strong> " + borrow.member.membershipNumber + " &nbsp;|&nbsp; " +
                    "<strong>Due:</strong> " + borrow.dueDate.toDateString();
                output.appendChild(li);
            }
        });
    };

    output.innerHTML = "";
    borrows.filter(b => !b.returned).forEach(function(borrow){
        let li = document.createElement("li");
        let book = books.find(b => b.id === borrow.bookCopy.bookId);
        li.innerHTML =
            "<strong>Book:</strong> " + book.title + " &nbsp;|&nbsp; " +
            "<strong>Member:</strong> " + borrow.member.name + " &nbsp;|&nbsp; " +
            "<strong>ID:</strong> " + borrow.member.membershipNumber + " &nbsp;|&nbsp; " +
            "<strong>Due:</strong> " + borrow.dueDate.toDateString();
        output.appendChild(li);
    });
});

document.getElementById("members-list-button").addEventListener("click", function(){
    let output = document.getElementById("output-list");
    let search = document.getElementById("list-search");
    search.style.display = "block";
    search.placeholder = "Search members...";
    search.value = "";
    search.oninput = function(){
        let keyword = this.value.toLowerCase();
        output.innerHTML = "";
        members.filter(m =>
            m.name.toLowerCase().includes(keyword) ||
            m.membershipNumber.includes(keyword)
        ).forEach(function(member){
            let li = document.createElement("li");
            li.innerHTML =
                "<strong>Name:</strong> " + member.name + " &nbsp;|&nbsp; " +
                "<strong>ID:</strong> " + member.membershipNumber + " &nbsp;|&nbsp; " +
                "<strong>Email:</strong> " + member.email + " &nbsp;|&nbsp; " +
                "<strong>Phone:</strong> " + member.phone;
            li.addEventListener("click", function(){
                showMemberHistory(member);
            });
            output.appendChild(li);
        });
    };

    output.innerHTML = "";
    members.forEach(function(member){
        let li = document.createElement("li");
        li.innerHTML =
            "<strong>Name:</strong> " + member.name + " &nbsp;|&nbsp; " +
            "<strong>ID:</strong> " + member.membershipNumber + " &nbsp;|&nbsp; " +
            "<strong>Email:</strong> " + member.email + " &nbsp;|&nbsp; " +
            "<strong>Phone:</strong> " + member.phone;
        li.addEventListener("click", function(){
            showMemberHistory(member);
        });
        output.appendChild(li);
    });
});

document.getElementById("add-book-button").addEventListener("click", function(){
    hideAllPages();
    showLibrarianNavbar();
    document.getElementById("add-book-page").style.display = "block";
    document.getElementById("book-title").value = "";
    document.getElementById("book-author").value = "";
    document.getElementById("book-isbn").value = "";
    document.getElementById("book-description").value = "";
    document.getElementById("book-publisher").value = "";
    document.getElementById("book-quantity").value = "";
});

document.getElementById("add-book-submit").addEventListener("click", function(){
    let title = document.getElementById("book-title").value;
    let author = document.getElementById("book-author").value;
    let isbn = document.getElementById("book-isbn").value;
    let description = document.getElementById("book-description").value;
    let publisher = document.getElementById("book-publisher").value;
    let quantity = parseInt(document.getElementById("book-quantity").value);

    if(!title || !author || !isbn || !description || !publisher || !quantity || isNaN(quantity) || quantity < 1){
        alert("Please fill in all fields");
        return;
    }

    let newBook = { id: books.length + 1, title: title, author: author, isbn: isbn,  description: description, publisher: publisher };
    books.push(newBook);

    for(let i = 0; i < quantity; i++){
        let newCopy = { id: bookCopies.length + 1, bookId: newBook.id, isAvailable: true };
        bookCopies.push(newCopy);
    }

    alert("Book Added Successfully! ID: " + newBook.id + " | Copies: " + quantity);
    showLibrarianPage()
});

document.getElementById("add-book-back").addEventListener("click", function(){
    showLibrarianPage();
});

let selectedBookToRemove = null;

document.getElementById("remove-book-button").addEventListener("click", function(){
    hideAllPages();
    showLibrarianNavbar();
    document.getElementById("remove-book-page").style.display = "block";

    let list = document.getElementById("remove-book-list");
    list.innerHTML = "";

    books.forEach(function(book){
        let li = document.createElement("li");
        li.textContent = book.title + " - " + book.author;

        li.addEventListener("click", function(){
            selectedBookToRemove = book;
            document.querySelectorAll("#remove-book-list li").forEach(l => l.style.background = "");
            li.style.background = "#dce8f7";
        });
        list.appendChild(li);
    });
});

document.getElementById("remove-book-submit").addEventListener("click", function(){
    if(selectedBookToRemove === null){
        alert("Select a book to remove");
        return;
    }

    books = books.filter(b => b.id !== selectedBookToRemove.id);
    selectedBookToRemove = null;

    alert("Book Removed Successfully!");
    hideAllPages();
    document.getElementById("librarian-page").style.display = "block";
});

document.getElementById("remove-book-back").addEventListener("click", function(){
    showLibrarianPage();
});

function showMemberHistory(member){
    hideAllPages();
    showLibrarianNavbar();
    document.getElementById("member-history-page").style.display = "block";
    document.getElementById("member-history-title").textContent = "History for: " + member.name;

    let currentList = document.getElementById("member-current-list");
    let historyList = document.getElementById("member-history-list");
    currentList.innerHTML = "";
    historyList.innerHTML = "";

    let memberBorrows = borrows.filter(b => b.member.membershipNumber === member.membershipNumber);

    if(memberBorrows.length === 0){
        historyList.innerHTML = "<li>No borrow history found.</li>";
        return;
    }

    memberBorrows.forEach(function(borrow){
        let book = books.find(b => b.id === borrow.bookCopy.bookId);
        let li = document.createElement("li");
        li.innerHTML =
            "<strong>Book:</strong> " + book.title + " &nbsp;|&nbsp; " +
            "<strong>Due:</strong> " + borrow.dueDate.toDateString() + " &nbsp;|&nbsp; " +
            "<strong>Status:</strong> " + (borrow.returned ? "Returned" : "Active");

        if(!borrow.returned){
            currentList.appendChild(li);
        } else {
            historyList.appendChild(li);
        }
    });

    if(currentList.innerHTML === "") currentList.innerHTML = "<li>No active borrows.</li>";
    if(historyList.innerHTML === "") historyList.innerHTML = "<li>No history yet.</li>";
}

document.getElementById("member-history-back").addEventListener("click", function(){
    showLibrarianPage();
});

function showBookBorrowers(book){
    hideAllPages();
    showLibrarianNavbar();
    document.getElementById("book-borrowers-page").style.display = "block";
    document.getElementById("book-borrowers-title").textContent = book.title;

    let availableCopies = bookCopies.filter(c => c.bookId === book.id && c.isAvailable).length;
    let totalCopies = bookCopies.filter(c => c.bookId === book.id).length;

    document.getElementById("book-borrowers-details").innerHTML =
        "<strong>Author:</strong> " + book.author + " &nbsp;|&nbsp; " +
        "<strong>ISBN:</strong> " + book.isbn + " &nbsp;|&nbsp; " +
        "<strong>Publisher:</strong> " + book.publisher + " &nbsp;|&nbsp; " +
        "<strong>Available Copies:</strong> " + availableCopies + " / " + totalCopies + "<br><br>" +
        "<strong>Description:</strong> " + (book.description || "No description available");

    let currentList = document.getElementById("book-current-borrowers");
    let historyList = document.getElementById("book-borrowers-history");
    currentList.innerHTML = "";
    historyList.innerHTML = "";

    let bookBorrows = borrows.filter(b => b.bookCopy.bookId === book.id);

    if(bookBorrows.length === 0){
        currentList.innerHTML = "<li>Nobody has borrowed this book yet.</li>";
        historyList.innerHTML = "<li>No history yet.</li>";
        return;
    }

    bookBorrows.forEach(function(borrow){
        let li = document.createElement("li");
        li.innerHTML =
            "<strong>Member:</strong> " + borrow.member.name + " &nbsp;|&nbsp; " +
            "<strong>ID:</strong> " + borrow.member.membershipNumber + " &nbsp;|&nbsp; " +
            "<strong>Due:</strong> " + borrow.dueDate.toDateString() + " &nbsp;|&nbsp; " +
            "<strong>Status:</strong> " + (borrow.returned ? "Returned" : "Active");

        if(!borrow.returned){
            currentList.appendChild(li);
        } else {
            historyList.appendChild(li);
        }
    });

    if(currentList.innerHTML === "") currentList.innerHTML = "<li>No active borrows.</li>";
    if(historyList.innerHTML === "") historyList.innerHTML = "<li>No history yet.</li>";
}

document.getElementById("book-borrowers-back").addEventListener("click", function(){
    showLibrarianPage();
});