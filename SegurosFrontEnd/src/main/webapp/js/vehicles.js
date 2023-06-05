class Vehicles {
    dom;
    state;
    
    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
    }
    
    render=()=> {
        const html = `
          ${this.renderList()}
        `;
        var rootContent = document.createElement('div');
        rootContent.id = 'vehicles';
        rootContent.innerHTML = html;
        return rootContent;
    }
    
    renderList=()=> {
      return `
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Modelos por marca</h1>
                    <div class="card">
                        <div class="card-body">
                            <div id="clients" class="table-responsive">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      `;
    };
    
    emptyEntity =()=> {
    }
}