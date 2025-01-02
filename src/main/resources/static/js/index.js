document.getElementById("filter_date").addEventListener("change", ({ target })=>window.location.assign(`/?filter=${target.value}`))
document.getElementById("open-modal-btn").addEventListener("click", ()=>document.querySelector(".modal__overlay").classList.remove("hidden"))
document.getElementById("close-modal-btn").addEventListener("click", ()=>document.querySelector(".modal__overlay").classList.add("hidden"))
