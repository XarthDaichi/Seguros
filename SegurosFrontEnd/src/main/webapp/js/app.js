class App{
    dom;
    modal;
    
    state;
    
    policies;
    
    constructor(){
        this.state={};
        this.dom=this.render(); 
        this.modal = new bootstrap.Modal(this.dom.querySelector('#app>#modal'));
        this.dom.querySelector('#app>#modal #apply').addEventListener('click',e=>this.login());
        this.renderBodyFiller();
        this.renderMenuItems();
        this.policies = new Policies();
    }
    
    render=()=>{
        const html = `
            ${this.renderMenu()}
            ${this.renderBody()} 
            ${this.renderFooter()}
            ${this.renderModal()}
        `;
       var rootContent = document.createElement('div');
       rootContent.id='app';
       rootContent.innerHTML=html;
       return rootContent;
    }
    
    renderMenu=()=>{
        return `
            <nav id="menu" class="navbar navbar-expand-lg p-0 navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand  font-italic font-weight-light  text-info" href="#">
                    <img src="images/logo.png" class="logo rounded-circle" alt="logo">
                    Policies
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuCollapse">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div id="menuCollapse" class="collapse navbar-collapse" >
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0" id='menuItems'>
                  </ul>
                </div>
              </div>
            </nav>
        `;
    }
    
    renderBody=()=>{
        return `
            <div id="body">   
            </div>          
        `;
    }
    
    renderFooter=()=>{
        return `
            <footer id="footer" class="bg-dark text-white mt-4 w-100 fixed-bottom">
                <div class="container-fluid py-2">

                    <div class="row">
                        <div class="col-md-2"><h5>Total Soft Inc.</h5></div>
                        <div class="col-md-7"><h4>
                            <i class="fab fa-twitter"></i>
                            <i class="fab fa-facebook"></i>
                            <i class="fab fa-instagram"></i></h4>
                        </div>
                        <div class="col-md-3 text-right small align-self-end">©2023 Tsf, Inc.</div>
                    </div>
                </div>
            </footer> 
        `;
    }
    
    renderModal=()=>{
        return `
            <div id="modal" class="modal fade" tabindex="-1">
               <div class="modal-dialog">
                   <div class="modal-content">
                       <div class="modal-header" >
                           <img class="img-circle" id="img_logo" src="images/user.png" style="max-width: 50px; max-height: 50px" alt="logo">
                           <span style='margin-left:4em;font-weight: bold;'>Login</span> 
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                       </div>
                       <form id="form" >
                       <div class="modal-body">
                           <div class="input-group mb-3">
                               <span class="input-group-text">Id</span>
                               <input type="text" class="form-control" id="identificacion" name="identificacion">
                           </div>  
                           <div class="input-group mb-3">
                               <span class="input-group-text">clave</span>
                               <input type="password" class="form-control" id="clave" name="clave">
                           </div>      
                       </div>
                       <div class="modal-footer">
                           <button id="apply" type="button" class="btn btn-primary" id="apply">Login</button>
                       </div>
                       <div class="input-group">
                           <span style="font-style: italic; margin-left: 2em;">No tiene cuenta? ... </span>
                           <a id="register" class="btn btn-info btn-block" style="margin-bottom: 15px; background-color: white; color:red; border:1px solid red" href="#">Registrese aquí</a>
                       </div>                
                       </form>                 
                   </div>         
               </div>          
           </div>   
        `;
    }
    
    renderBodyFiller=()=>{
        var html= `
            <div id='bodyFiller' style='margin-left: 10%; margin-top:100px; width: 80%; text-align: center; font-size: 1.5em'>
                <p>Informacón de los paises del mundo.</p>
                <img src="images/filler.jpg" class="filler rounded-circle" alt="filler">
            </div>
        `;
        this.dom.querySelector('#app>#body').replaceChildren();
        this.dom.querySelector('#app>#body').innerHTML=html; 
    }
    
    renderMenuItems=()=>{
        var html='';
        if(globalstate.user===null){
            html+=`
              <li class="nav-item">
                  <a class="nav-link" id="login" href="#" data-bs-toggle="modal"> <span><i class="fa fa-address-card"></i></span> Login </a>
              </li>
            `;
        }else{
            if(globalstate.user.rol==='0'){//Client
                html+=`
                    <li class="nav-item">
                        <a class="nav-link" id="policies" href="#"> <span><i class="fas fa-file-alt"></i></span> Policies </a>
                    </li>
                `;
            }
            if(globalstate.user.rol==='1'){//Admin
                html+=`
                `;
            }
            html+=`
              <li class="nav-item">
                  <a class="nav-link" id="logout" href="#" data-bs-toggle="modal"> <span><i class="fas fa-power-off"></i></span> Logout (${globalstate.user.identificacion}) </a>
              </li>
            `;
        };
        this.dom.querySelector('#app>#menu #menuItems').replaceChildren();
        this.dom.querySelector('#app>#menu #menuItems').innerHTML=html;
        this.dom.querySelector("#app>#menu #menuItems #policies")?.addEventListener('click', e=>this.policiesShow());   
        this.dom.querySelector("#app>#menu #menuItems #login")?.addEventListener('click', e=>this.modal.show());  
        this.dom.querySelector("#app>#menu #menuItems #logout")?.addEventListener('click', e=>this.logout());
        if(globalstate.user!==null){
            switch(globalstate.user.rol){
                case '0'://CLient
                    this.policiesShow();
                    break;
            }
        }
    }
    
    policiesShow=()=>{
        this.dom.querySelector('#app>#body').replaceChildren(this.policies.dom);
        this.policies.list();
    }
    
    login= async ()=>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector("#form"))).entries());
        candidate.rol='0';
        globalstate.user = candidate;
        this.modal.hide();
        this.renderMenuItems();
    }
    
    logout= async ()=>{
        globalstate.user=null;
        this.dom.querySelector('#app>#body').replaceChildren();
        this.renderBodyFiller();
        this.renderMenuItems();         
        let request = new Request(`${backend}/login`, {method: 'DELETE', headers: { }});
    }
}
