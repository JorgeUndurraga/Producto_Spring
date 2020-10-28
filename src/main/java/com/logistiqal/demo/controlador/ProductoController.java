package com.logistiqal.demo.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.logistiqal.demo.modelo.Producto;
import com.logistiqal.demo.servicio.ProductoService;
import com.logistiqal.demo.vo.ProductoVO;
import com.logistiqal.demo.util.Util;


@Controller
public class ProductoController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService svc;

//	@Autowired
//	private SecurityService secSvc;

	@GetMapping("/editarForm")
	public ModelAndView editarForm(Model model, @RequestParam String idUsuario, RedirectAttributes ra) {
		
//		if (!secSvc.isLoggedIn())
		
		return new ModelAndView("redirect:/login");
		
		ProductoVO respuestaServicio = new ProductoVO();
		
		respuestaServicio.setMensaje("No se pudo cargar la vista de edición, intente nuevamente.");
		
		try {
			Integer id = (Integer.parseInt(idUsuario));
			respuestaServicio = svc.findById(id);
			model.addAttribute("mensaje", respuestaServicio.getMensaje());
			model.addAttribute("VO", respuestaServicio.getProductos().get(0));
			return new ModelAndView("editar");
		} catch (Exception e) {
			log.error("Error en UsuarioController eliminar", e);
		}
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		return new ModelAndView("redirect:/home");
	}

	@PostMapping("/editar")
	public ModelAndView editar(@ModelAttribute Producto producto, RedirectAttributes ra) {
//		if (!secSvc.isLoggedIn())
			return new ModelAndView("redirect:/login");
		ProductoVO respuestaServicio = svc.update(producto);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		if (respuestaServicio.getCodigoError().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/editarForm");
		}
	}

	@GetMapping("/agregarForm")
	public String agregarForm(Model model) {
//		if (!secSvc.isLoggedIn())
			return "redirect:/login";
		return "agregar";
	}

	@PostMapping("/agregar")
	public ModelAndView agregarSubmit(@ModelAttribute Producto producto, RedirectAttributes ra) {
//		if (!secSvc.isLoggedIn())
			return new ModelAndView("redirect:/login");
		ProductoVO respuestaServicio = svc.add(producto);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		if (respuestaServicio.getCodigoError().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/agregarForm");
		}
	}

	@GetMapping("/eliminar")
	public ModelAndView eliminar(Model model, @RequestParam String idUsuario, RedirectAttributes ra) {
//		if (!secSvc.isLoggedIn())
			return new ModelAndView("redirect:/login");
		ProductoVO respuestaServicio = new ProductoVO();
		respuestaServicio.setMensaje("No se pudo eliminar al usuario, intente nuevamente.");
		try {
			Producto eliminado = new Producto();
			eliminado.setCodigo(Integer.parseInt(idUsuario));
			respuestaServicio = svc.delete(eliminado);
			ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
			return new ModelAndView("redirect:/home");
		} catch (Exception e) {
			log.error("Error en ProductoController eliminar", e);
		}
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		ra.addAttribute("VO", respuestaServicio);
		return new ModelAndView("redirect:/home");
	}

	@GetMapping({ "/home" })
	public String home(Model model, @RequestParam(defaultValue = "1") Integer p) {
//		if (!secSvc.isLoggedIn())
			return "redirect:/login";
		model.addAttribute("usuarioConectado", String.format("Conectado como %s", secSvc.getUsuarioConectado().getNombre()));
		Integer totalPaginas = (int) svc.getPageCount(6).getValor();
		model.addAttribute("paginas", Util.getArregloPaginas(p, totalPaginas));
		model.addAttribute("paginaActual", p);
		model.addAttribute("VO", svc.getPage(p-1, 6));
		return "home";
	}

}
