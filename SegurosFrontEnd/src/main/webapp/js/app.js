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
        const html=`
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
            <h1>Lorem Ipsum</h1>
        `;
    }
    
    renderBody=()=>{
        return `
            <h1>Lorem Ipsum</h1>
        `;
    }
    
    renderFooter=()=>{
        return `
            <footer id="footer">
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
            <h1>Lorem Ipsum</h1>
        `;
    }
    
    renderBodyFiller=()=>{
        var html=`
            <div id='bodyFiller' style='margin-left: 10%; margin-top:100px; width: 80%; text-align: center; font-size: 1.5em'>
            <p>Lorem Ipsum.</p>
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
                        <a class="nav-link" id="countries" href="#"> <span><i class="fas fa-file-alt"></i></span> Countries </a>
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
        this.dom.querySelector('#app>#menu #menuItems #policies')?.addEventListener('click', e=>this.policiesShow());
        this.dom.querySelector("#app>#menu #menuItems #login")?.addEventListener('click', e=>this.modal.show());  
        this.dom.querySelector("#app>#menu #menuItems #logout")?.addEventListener('click', e=>this.logout());
        if(globalstate.user!==null){
            switch(globalstate.user.rol){
                case '0': //Client
                    this.policiesShow();
                    break;
                case '1': //Admin
                    break;
            }
        }
    }
    
    policiesShow=()=>{
        this.dom.querySelector('#app>#body').replaceChildren(this.policies.dom);
        this.policies.list();
    }
    
    login= async ()=>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector('#form'))).entries() );
        candidate.rol='0';
        globalstate.user = candidate;
        this.modal.hide();
        this.renderMenuItems();
    }
    
    logout= async() =>{
        globalstate.user=null;
        this.dom.querySelector('#app>#body').replaceChildren();
        this.renderBodyFiller();
        this.renderMenuItems();
        let request = new Request(`${backend}/login`, {method: 'DELETE', headers: { }});
    }
}
