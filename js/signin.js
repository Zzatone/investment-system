window.addEventListener('load',()=>{
    const goSignIn=document.querySelector('#goSignIn');
    const goSignUp=document.querySelector('#goSignUp');
    const container=document.querySelector('.container');
    goSignIn.addEventListener('click',function(){
        container.classList.remove('switch');
    })
    goSignUp.addEventListener('click',()=>{
        container.classList.add('switch');
    })
})