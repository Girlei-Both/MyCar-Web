const menus = document.querySelectorAll(".menu");

menus.forEach(menu => {
    menu.addEventListener("click", function(){
        if(menu.classList.contains("selecionado")){
            return;
        }

        selecionarMenu(menu)
        mostrarConteudoDoMenu(menu)

    });
});

function selecionarMenu(menu){
    const menuSelecionado = document.querySelector(".menu.selecionado");
    menuSelecionado.classList.remove("selecionado");
    menu.classList.add("selecionado");
}

function mostrarConteudoDoMenu(menu){
    const conteudoSelecionado = document.querySelector(".informacao.selecionado");
    conteudoSelecionado.classList.remove("selecionado");
    const idDoConteudoDaAba = `informacao-${menu.id}`;
    const conteudoMostrado = document.getElementById(idDoConteudoDaAba);
    conteudoMostrado.classList.add("selecionado");
}