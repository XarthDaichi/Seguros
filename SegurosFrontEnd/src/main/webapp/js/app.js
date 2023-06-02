class App{
    dom;
    
    modal;
    registerUserModal;
    
    state;
    
    policies;
    
    constructor(){
        this.state={};
        this.dom=this.render(); 
        this.modal = new bootstrap.Modal(this.dom.querySelector('#app>#modal'));
        this.registerUserModal = new bootstrap.Modal(this.dom.querySelector('#app>#registerUserModal'));
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
                    <img src="images/Logotipo.png" class="logo rounded-circle" style="max-width: 100px; max-height: 100px" alt="logo">
                    Seguros
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
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <h4>Sobre nosotros</h4>
                            <p>
                                Somos una empresa
                                multinacional comprometida a asegurar de manera eficiente sus bienes.
                            </p>
                        </div>
                        <div class="col-md-4">
                            <h4>Contactenos</h4>
                            <ul>
                              <li><i class="fas fa-map-marker-alt"></i> Heredia, Costa Rica</li>
                              <li><i class="fas fa-phone"></i> (506) 6304-0703</li>
                              <li><i class="fas fa-envelope"></i> jorgedc1304@gmail.com</li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <h4>Redes Sociales</h4>
                            <ul class="social">
                                <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                                <li><a href="#"><i class="fab fa-linkedin-in"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="bottom">
                    <p>&copy; 2023 - Todos los derechos reservados</p>
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
                            <img class="img-circle" id="img_logo" src="images/profile.png" style="max-width: 50px; max-height: 50px" alt="logo">
                            <span style='margin-left:4em;font-weight: bold;'>Login</span> 
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="form" >
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Id</span>
                                    <input type="text" class="form-control" id="id" name="id">
                                </div>  
                                <div class="input-group mb-3">
                                    <span class="input-group-text">clave</span>
                                    <input type="password" class="form-control" id="password" name="password">
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
        
            <div id="registerUserModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" >
                            <img class="img-circle" id="img_logo" src="images/profile.png" style="max-width: 50px; max-height: 50px" alt="logo">
                            <span style='margin-left:4em;font-weight: bold;'>Register</span> 
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="form" >
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Id</span>
                                    <input type="text" class="form-control" id="id" name="id" placeholder="ID" required>
                                </div> 
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">clave</span>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">clave</span>
                                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">clave</span>
                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone number" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">clave</span>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Email address" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">clave</span>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Email address" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="applyRegister" type="button" class="btn btn-primary" id="applyRegister">Register</button>
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
                <p>Bienvenidos a Seguros.</p>
                <img src="images/filler.png" alt="filler">
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
        
        this.dom.querySelector("#app>#modal #register")?.addEventListener('click',e=>{
            this.modal.hide();
            this.registerUserModal.show();
        });
        
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
        candidate.name = "";
        candidate.cellphone = "";
        candidate.email = "";
        candidate.cardNumber = "";
        candidate.administrator = false;
                
        const userRequest = new Request(`${backend}/login`,{
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(candidate)
        });
        
        try{
            
            const response = await fetch(userRequest);
            
            if(!response.ok){
                const error = await response.text();
                console.log("LOGIN ERROR"+error);
                return;
            }
            
            //console.log("LOGIN SUCCESSFUL");
            const user = await response.json();
            globalstate.user = user;
            
            /*if(globalstate.user.administrator === '0'){
                //Verify usability of this clause
            }*/ //Might be unnecessary needs checking
            
            this.modal.hide();
            this.renderMenuItems();
            
        }catch(err){
            console.error(err);
        }
    }
    
    logout= async ()=>{
        //Invoque backend for login
        globalstate.user=null;
        this.dom.querySelector('#app>#body').replaceChildren();
        this.renderBodyFiller();
        this.renderMenuItems();         
        let request = new Request(`${backend}/login`, {method: 'DELETE', headers: { }});
    }
}
