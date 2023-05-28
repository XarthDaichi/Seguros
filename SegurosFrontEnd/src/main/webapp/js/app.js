/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

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
            <h1>Lorem Ipsum</h1>
        `;
    }
    
    renderModal=()=>{
        return `
            <h1>Lorem Ipsum</h1>
        `;
    }
    
    renderBodyFiller=()=>{
        var html=`
            <h1>Lorem Ipsum</h1>
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
