package mx.com.ananda.cronos.juno.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.cronos.juno.model.dto.http.DetalleCompraDTO;
import mx.com.ananda.cronos.juno.model.dto.http.ItemFotoDTO;
import mx.com.ananda.cronos.juno.model.dto.http.OrdenCompraDTO;
import mx.com.ananda.cronos.juno.response.OrdenCompraResponse;
import mx.com.ananda.cronos.juno.service.interfaces.IDetalleOrdenService;
import mx.com.ananda.cronos.juno.service.interfaces.IOrdenCompraService;
import mx.com.ananda.cronos.juno.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("ananda/cronos1_3/juno1_5/orden")
public class OrdenCompraController {

    @Autowired
    private IOrdenCompraService sOrden;


    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO') OR hasRole('COMPRAS')")
    @GetMapping("")
    public ResponseEntity<OrdenCompraResponse> traerOrdenes(
            @RequestParam(value = "pageNO",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value= "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "idOrdenCompra") String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sOrden.getAllOrders(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS')")
    @GetMapping("/cardName")
    public ResponseEntity<OrdenCompraResponse> traerOrdenesByProveedorNombre(
            @RequestParam(value = "pageNO",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value= "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "cardName",defaultValue = "") String cardName
    ){
       return new ResponseEntity<>(sOrden.getAllOrdersByCardName(pageNumber,pageSize,cardName),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS')")
    @GetMapping("/cardCode")
    public ResponseEntity<OrdenCompraResponse> traerOrdenesByProveedorNumero(
            @RequestParam(value = "pageNO",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value= "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "cardCode",defaultValue = "") String cardCode
    ){
        return new ResponseEntity<>(sOrden.getAllOrdersByCardCode(pageNumber,pageSize,cardCode),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO') OR hasRole('COMPRAS')")
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraDTO> traerOrdenById(@PathVariable(value = "id") Long idOrden){
        return new ResponseEntity<>(sOrden.getOrderById(idOrden),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO') OR hasRole('COMPRAS')")
    @GetMapping("/docNum")
    public ResponseEntity<OrdenCompraDTO> traerOrdenByDocNum(@RequestParam(value = "docNum") Long docNum){
        return new ResponseEntity<>(sOrden.getOrderByDocNum(docNum),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS')")
    @GetMapping("/assign-order/{id}")
    public ResponseEntity<OrdenCompraDTO> asignarOrdenSAP(@PathVariable(value = "id") Long id,
                                                          @RequestParam(value = "docNum") Long docNum){
        return new ResponseEntity<>(sOrden.assignOrderSAP(id,docNum),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPRAS')")
    @GetMapping("/details/{id}")
    public ResponseEntity<List<DetalleCompraDTO>> traerDetallesOrden(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(sOrden.getDetailsOrder(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('FOTO')")
    @GetMapping("/photo/{id}")
    public ResponseEntity<List<ItemFotoDTO>> traerFotosOrden(@PathVariable(value = "id")Long id){
        return new ResponseEntity<>(sOrden.getDetailsFoto(id),HttpStatus.OK);
    }


}
