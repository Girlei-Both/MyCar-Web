let clienteImg = document.getElementById('clienteImg');
let empreendedorImg = document.getElementById('empreendedorImg');

function atualizarValorInput(valor) {

    // Normaliza a opacidade de ambas as imagens
    clienteImg.style.opacity = 1;
    empreendedorImg.style.opacity = 1;

    // Lógica para atualizar o valor conforme necessário
    document.getElementById('tipoInput').value = valor;

    // Define a opacidade da imagem clicada para 0.5 (opaca)
    if (valor === 1) {
        clienteImg.style.opacity = 0.5;
    } else if (valor === 2) {
        empreendedorImg.style.opacity = 0.5;
    }
}

function limparInputs() {

    var inputs = document.querySelectorAll('input');
    inputs.forEach(function (input) {
        input.value = "";
    });

    var select = document.querySelector('select');
    select.selectedIndex = 0;
}