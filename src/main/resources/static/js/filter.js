window.onload = ()=>{
  alert("LoadedJS")
}

document
  .getElementById("filter_date")
  .addEventListener("change", ({ target })=>{
    window.location.href = `/?filter=${target.value}`
  })
