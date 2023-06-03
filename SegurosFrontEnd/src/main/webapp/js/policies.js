class Policies {
    dom;
    createPolicyModal;

    state;

    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
        this.createPolicyModal = new bootstrap.Modal(this.dom.querySelector("#createPolicyModal"));
        this.dom.querySelector("#createPolicy").addEventListener('click', e=>this.showModal());
        this.dom.querySelector("#searchButton").addEventListener('click', e=>this.search());
    }

    render = () => {
        const html = `
          ${this.renderList()}
          ${this.renderModal()}
        `;
        var rootContent = document.createElement('div');
        rootContent.id = 'policies';
        rootContent.innerHTML = html;
        return rootContent;
    }
    
    //RenderPoliciesList: Pone en cada row, los datos de state.policies.
    
    //RenderPolicies: Hace la petición de polizas al backend

    renderList = () => {
      return `
        <div class="container mt-5">
          <div class="row">
            <div class="col-md-12">
              <h1 class="text-center">Pólizas de Seguro</h1>
              <div class="d-flex justify-content-end mb-3">
                <button class="btn btn-primary" id="createPolicy" data-bs-toggle="modal" data-bs-target="#crearPolizaModal">
                  <i class="fas fa-plus"></i> Crear Póliza
                </button>
              </div>
              <div class="card">
                <div class="card-body">
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" id="searchButton" placeholder="Buscar por número de placa">
                    <button class="btn btn-outline-secondary" type="button" id="botonBusqueda">Buscar</button>
                  </div>
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>Número de Póliza</th>
                          <th>Número de Placa</th>
                          <th>Fecha</th>
                          <th>Automóvil</th>
                          <th>Valor</th>
                          <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                        <!-- Filas de datos de muestra -->
                        <tr>
                          <td>1234</td>
                          <td>ABC123</td>
                          <td>2023-06-01</td>
                          <td>Honda Civic</td>
                          <td>$500</td>
                          <td>
                            <button class="btn btn-sm btn-info">Ver</button>
                          </td>
                        </tr>
                        <tr>
                          <td>5678</td>
                          <td>XYZ789</td>
                          <td>2023-05-30</td>
                          <td>Toyota Corolla</td>
                          <td>$800</td>
                          <td>
                            <button class="btn btn-sm btn-info">Ver</button>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      `;
    };

    renderModal = () => {
      return `
        <div id="createPolicyModal" class="modal fade" tabindex="-1">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Crear Póliza de Seguro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <ul class="nav nav-tabs" role="tablist">
                  <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="datosBasicosTab" data-bs-toggle="tab" data-bs-target="#datosBasicos" type="button" role="tab" aria-controls="datosBasicos" aria-selected="true">Datos Básicos</button>
                  </li>
                  <li class="nav-item" role="presentation">
                    <button class="nav-link" id="coberturasTab" data-bs-toggle="tab" data-bs-target="#coberturas" type="button" role="tab" aria-controls="coberturas" aria-selected="false">Coberturas</button>
                  </li>
                </ul>
                <div class="tab-content mt-3">
                  <div class="tab-pane fade show active" id="datosBasicos" role="tabpanel" aria-labelledby="datosBasicosTab">
                    <form>
                      <div class="mb-3">
                        <label for="numeroPlaca" class="form-label">Número de Placa del Vehículo</label>
                        <input type="text" class="form-control" id="numeroPlaca" placeholder="Ingrese el número de placa del vehículo" required>
                      </div>
                      <div class="mb-3">
                        <label for="anioVehiculo" class="form-label">Año del Vehículo</label>
                        <input type="text" class="form-control" id="anioVehiculo" placeholder="Ingrese el año del vehículo" pattern="^((188[6-9])|(18[9][0-9])|(19[0-9]{2})|(200[0-9])|(201[0-9])|(202[0-3]))$" required>
                      </div>
                      <div class="mb-3">
                        <label for="marcaModelo" class="form-label">Marca y Modelo del Vehículo</label>
                        <select class="form-select" id="marcaModelo" required>
                          <option value="">Seleccione la marca y modelo del vehículo...</option>
                          <!-- Opciones de marca y modelo aquí con renderVehiculos-->
                        </select>
                      </div>
                      <div class="mb-3">
                        <label for="valorVehiculo" class="form-label">Valor del Vehículo en Colones</label>
                        <input type="text" class="form-control" id="valorVehiculo" placeholder="Ingrese el valor del vehículo en colones" required>
                      </div>
                      <div class="mb-3">
                        <label class="form-label">Método de Pago</label>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="metodoPago" id="Trimestral" required>
                          <label class="form-check-label" for="Trimestral">Pago Trimestral</label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="metodoPago" id="Semestral" required>
                          <label class="form-check-label" for="Semestral">Pago Semestral</label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="metodoPago" id="Anual" required>
                          <label class="form-check-label" for="Anual">Pago Anual</label>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="tab-pane fade" id="coberturas" role="tabpanel" aria-labelledby="coberturasTab">
                    <div class="scrollable" id="listaCoberturas">
                      <!-- Renderizar las categorías y sus coberturas en load -->
                    </div>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button id="siguienteBtn" type="button" class="btn btn-primary">Siguiente</button>
              </div>
            </div>
          </div>
        </div>
      `;
    };

    showModal = () => {
        this.createPolicyModal.show();
    };

    emptyEntity = () => {

    }

    makenew = () => {
        this.state.mode = 'A';
        this.showModal();
    }

    search = () => {
//        const searchInput = this.dom.querySelector('#searchInput').value.toLowerCase();
//
//        let filteredPolizas;
//        if (searchInput === '') {
//            filteredPolizas = this.state.polizas;
//        } else {
//            filteredPolizas = this.state.polizas.filter(poliza => {
//                const { numero } = poliza;
//                return numero.toLowerCase().includes(searchInput);
//            });
//        }
//
//        this.renderPolizasTable(filteredPolizas);
    }
}
