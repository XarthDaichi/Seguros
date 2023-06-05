class App{
    dom;
    
    modal;
    registerUserModal;
    editUserModal;
    
    state;
    
    policies;
    administrator;
    
    constructor(){
        this.state={};
        this.dom=this.render();
        this.renderBodyFiller();
        this.renderMenuItems();
        
        this.policies = new Policies();
        this.administrator = new Administrator(); // This line breaks front-end, class needs checking
        
        this.modal = new bootstrap.Modal(this.dom.querySelector('#app>#modal'));
        this.registerUserModal = new bootstrap.Modal(this.dom.querySelector('#app>#registerUserModal'));
        this.editUserModal = new bootstrap.Modal(this.dom.querySelector('#app>#editUserModal'));
        
        this.dom.querySelector('#app>#modal #apply').addEventListener('click',e=>this.login());
        this.dom.querySelector('#app>#registerUserModal #applyRegister').addEventListener('click',e=>this.register());
        this.dom.querySelector('#app>#editUserModal #applyChanges').addEventListener('click', e=>this.editUser());
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
            <div id="body" style="margin-bottom: 51px">   
            </div>          
        `;
    }
    
    renderFooter=()=>{
        return `
            <footer id="footer" class="bg-dark text-white mt-4 w-100">
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
                                <li><a href="https://www.facebook.com/EscueladeInformaticaUNA"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="https://www.instagram.com/escinf.una/"><i class="fab fa-instagram"></i></a></li>
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
                                    <span class="input-group-text">ID</span>
                                    <input type="text" class="form-control" id="id" name="id">
                                </div>  
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Password</span>
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>      
                            </div>
                            <div class="modal-footer">
                                <button id="apply" type="button" class="btn btn-primary" id="apply">Login</button>
                            </div>
                            <div class="input-group">
                                <span style="font-style: italic; margin-left: 2em; margin-right: 10px">No tiene cuenta?   </span>
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
                            <span style='margin-left:4em;font-weight: bold;'>Registro</span> 
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="registerUserForm" >
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">ID</span>
                                    <input type="text" class="form-control" id="id" name="id" required>
                                </div> 
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Password</span>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Name</span>
                                    <input type="text" class="form-control" id="name" name="name" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Phone number</span>
                                    <input type="text" class="form-control" id="cellphone" name="cellphone" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Email</span>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
        
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Card number</span>
                                    <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="applyRegister" type="button" class="btn btn-primary" id="applyRegister">Register</button>
                            </div>               
                        </form>                 
                    </div>         
                </div>          
            </div>
        
            <div id="editUserModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" >
                            <img class="img-circle" id="img_logo" src="images/profile.png" style="max-width: 50px; max-height: 50px" alt="logo">
                            <span style='margin-left:4em;font-weight: bold;'>Editar usuario</span> 
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="editUserForm" >
                            <div class="modal-body" id="editUserModalBody">
                                
                            </div>
                            <div class="modal-footer">
                                <button id="applyChanges" type="button" class="btn btn-primary" id="applyChanges">Apply</button>
                            </div>               
                        </form>                 
                    </div>         
                </div>          
            </div>
        `;
    }
    
    renderUserData=()=>{
        this.dom.querySelector('#editUserModalBody').replaceChildren();
        this.dom.querySelector('#editUserModalBody').innerHTML = `
            <div class="input-group mb-3">
                <span class="input-group-text">ID</span>
                <input type="text" class="form-control" id="id" name="id" value="${globalstate.user.id}" readonly>
            </div> 
        
            <div class="input-group mb-3">
                <span class="input-group-text">Password</span>
                <input type="password" class="form-control" id="password" name="password" value="${globalstate.user.password}" required>
            </div>
        
            <div class="input-group mb-3">
                <span class="input-group-text">Name</span>
                <input type="text" class="form-control" id="name" name="name" value="${globalstate.user.name}" required>
            </div>
        
            <div class="input-group mb-3">
                <span class="input-group-text">Phone number</span>
                <input type="text" class="form-control" id="cellphone" name="cellphone" value="${globalstate.user.cellphone}" required>
            </div>
        
            <div class="input-group mb-3">
                <span class="input-group-text">Email</span>
                <input type="email" class="form-control" id="email" name="email" value="${globalstate.user.email}" required>
            </div>
        
            <div class="input-group mb-3">
                <span class="input-group-text">Card number</span>
                <input type="text" class="form-control" id="cardNumber" name="cardNumber" value="${globalstate.user.cardNumber}" required>
            </div>
        `;
        this.editUserModal.show();
    }
    
    renderBodyFiller=()=>{
        var html= `
            <div id='bodyFiller' style='margin-left: 10%; margin-top:100px; width: 80%; text-align: center; font-size: 1.5em'>
                <p>Bienvenidos a Seguros.</p>
                <img src="images/filler.png" alt="filler" style="max-width: 50%; max-height: 50%">
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
                  <a class="nav-link" id="login" href="#" data-bs-toggle="modal"> <span><i class="fas fa-sign-in-alt"></i></span> Login </a>
              </li>
            `;
        }else{
            if(globalstate.user.administrator===false){//Client
                html+=`
                    <li class="nav-item">
                        <a class="nav-link" id="policies" href="#"> <span><i class="fas fa-th-list"></i></span> Policies </a>
                    </li>
                `;
            }
            if(globalstate.user.administrator===true){//Admin
                html+=`
                    <li class="nav-item">
                        <a class="nav-link" id="clientsPolicies" href="#"> <span><i class="fas fa-th-list"></i></span> Clients' policies </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="coverages" href="#"><i class="fas fa-list-ul"></i> Categories and Coverages </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="vehicles" href="#"><i class="fas fa-car"></i> Vehicles </a>
                    </li>
                `;
            }
            html+=`
                <li class="nav-item">
                    <a class="nav-link" id="userProfile" href="#" data-bs-toggle="modal"> <span><i class="fas fa-user"></i></span> ${globalstate.user.name} </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="logout" href="#" data-bs-toggle="modal"> <span><i class="fas fa-sign-out-alt"></i></span> Logout </a>
                </li>
            `;
        };
        this.dom.querySelector('#app>#menu #menuItems').replaceChildren();
        this.dom.querySelector('#app>#menu #menuItems').innerHTML=html;
        
        this.dom.querySelector("#app>#menu #menuItems #policies")?.addEventListener('click', e=>this.policiesShow());
        this.dom.querySelector("#app>#menu #menuItems #clientsPolicies")?.addEventListener('click', e=>this.administratorShow());
        
        this.dom.querySelector("#app>#menu #menuItems #login")?.addEventListener('click', e=>this.modal.show());  
        this.dom.querySelector("#app>#menu #menuItems #logout")?.addEventListener('click', e=>this.logout());
        
        this.dom.querySelector("#app>#modal #register")?.addEventListener('click',e=>{
            this.modal.hide();
            this.registerUserModal.show();
        });
        
        if(globalstate.user!==null){
            switch(globalstate.user.administrator){
                case false://Client
                    this.dom.querySelector("#app>#menu #menuItems #userProfile")?.addEventListener('click', e=>this.renderUserData());
                    this.policiesShow();
                    break;
                case true://Admin
                    this.dom.querySelector("#app>#menu #menuItems #userProfile")?.addEventListener('click', e=>this.renderUserData());
                    this.administratorShow();
                    break;
            }
        }
    }
    
    policiesShow=()=>{
        this.dom.querySelector('#app>#body').replaceChildren(this.policies.dom);
        this.policies.renderPolicies();
    }
    
    administratorShow=()=>{
        this.dom.querySelector('#app>#body').replaceChildren(this.administrator.dom);
        this.administrator.renderClients();
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
                console.log("LOGIN ERROR - "+error);
                return;
            }
            
            console.log("LOGIN SUCCESSFUL");
            const user = await response.json();
            globalstate.user = user;
            
            this.modal.hide();
            this.renderMenuItems();
            this.clearLoginModal();
        }catch(err){
            console.error(err);
        }
    }
    
    register = async() =>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector("#registerUserForm"))).entries());
        candidate.administrator = false;
        
        const registerUserRequest = new Request(`${backend}/clients`,{
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(candidate)
        });
        
        try{
            const response = await fetch(registerUserRequest);
            
            if(!response.ok){
                const error = await response.text();
                console.log("REGISTER ERROR - "+error);
                return;
            }
            
            console.log("REGISTER SUCCESSFUL");
            this.registerUserModal.hide();
            
        }catch(err){
            console.error(err);
        }
    }
    
    editUser= async() =>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector("#editUserForm"))).entries());
        candidate.administrator = false;
        
        const updateUserRequest = new Request(`${backend}/clients`,{
            method:'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(candidate)
        });
        
        try{
            const response = await fetch(updateUserRequest);
            
            if(!response.ok){
                const error = await response.text();
                console.log("UPDATE USER ERROR - "+error);
                return;
            }
            
            console.log("UPDATE USER SUCCESSFUL");
            globalstate.user.name = candidate.name;
            this.renderMenuItems();
            this.editUserModal.hide();
        }catch(err){
            console.error(err);
        }
    }
    
    logout= async ()=>{
        globalstate.user=null;
        this.dom.querySelector('#app>#body').replaceChildren();
        this.renderBodyFiller();
        this.renderMenuItems();         
        const request = new Request(`${backend}/login`, {method: 'DELETE', headers: { }});
        const response = await fetch(request);
        
        if(!response.ok){
            console.log(`LOGOUT ERROR - ${response.status}`);
        }
        this.clearLoginModal();
    }
    
    clearLoginModal = ()=>{
        this.dom.querySelector('#form #id').value = '';
        this.dom.querySelector('#form #password').value = '';
    }
    
    clearRegisterModal = ()=>{
        this.dom.querySelector('#form #id').value = '';
        this.dom.querySelector('#form #password').value = '';
        this.dom.querySelector('#form #name').value = '';
        this.dom.querySelector('#form #phone').value = '';
        this.dom.querySelector('#form #email').value = '';
        this.dom.querySelector('#form #cardNumber').value = '';
    }
}
