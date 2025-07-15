
    document.querySelector("form").addEventListener("submit", function(event) {
        event.preventDefault(); // Impede envio real

        const email = document.getElementById("InputEmail").value;
        const address = document.getElementById("InputAddress").value;
        const name = document.getElementById("InputName").value;
        const number = document.getElementById("InputNumber").value;
        const password = document.getElementById("InputPassword1").value;
        const team = document.getElementById("InputTeam").value;

        // Capturar radio selecionado (género)
        const gender = document.querySelector('input[name="gender"]:checked')?.value;

        // Capturar checkbox "I Agree"
        const agree = document.getElementById("invalidCheck").checked;

        console.log("Dados do formulário:");
        console.log("Email:", email);
        console.log("Address:", address);
        console.log("Name:", name);
        console.log("Number:", number);
        console.log("Password:", password);
        console.log("Team:", team);
        console.log("Gender:", gender);
        console.log("Agreed with terms:", agree ? "Yes" : "No");

        alert("Dados capturados com sucesso! Verifica a consola.");
    });
