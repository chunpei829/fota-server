package com.arimacomm.fotaServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import com.arimacomm.fotaServer.Product;
import com.arimacomm.fotaServer.ProductRepository;
import com.arimacomm.fotaServer.Version;
import com.arimacomm.fotaServer.VersionRepository;
import com.arimacomm.fotaServer.Md5hash;

@Controller
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VersionRepository versionRepository;
	
	@Autowired
	private DeltaRepository deltaRepository;
	
	@GetMapping(path="/home")//Show all products
	public String getAllProducts(Model product) {
		log.info("[Jim] asscess /home");
    	Iterable<Product> products = productRepository.findAll();
    	product.addAttribute("products", products);
    	return "home";
    }
	
	@GetMapping("/add_product") //Create a product
    public String productForm(Model product) {
        product.addAttribute("product", new Product());
        return "add_product";
    }
	
	@PostMapping("/product_result") //Create a product
    public String productSubmit(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return "add_product";
    	}
    	else {
	    	productRepository.save(product);
	        return "redirect:/product/" + product.getId();
    	}
    }
	
//	@GetMapping("/product") 
//	public String showProduct(@RequestParam Integer id, Model model) {
//		log.info("[Jim] asscess /product?id=" + id);
//		Product product = productRepository.findById(id).get();
//		log.info("[Jim] product = " + product);
//		model.addAttribute("product", product);
//		return "product";
//	}
	
	@GetMapping("/product/{id}") //Show a product
	public String showProduct(@PathVariable("id") Integer id, Model model) {
		log.info("[Jim] asscess /product/" + id);
		Product product = productRepository.findById(id).get();
		log.info("[Jim] product = " + product);
		Iterable<Version> productVersions = versionRepository.findByProductId(id);
		model.addAttribute("product", product);
		model.addAttribute("productVersions", productVersions);
		return "product";
	}
	

	@GetMapping("product/{productId}/add_version") //Create product's version
    public String versionForm(@PathVariable("productId") Integer productId, Model model) {
		log.info("[Jim] asscess /product/" + productId + "/add_version");
		Version version = new Version();
		version.setProductId(productId);
        model.addAttribute("version", version);
        return "add_version";
    }
	
	@PostMapping("/product/{productId}/version_result") //Create product's version
    public String verionSubmit(@Valid @ModelAttribute Version version, 
    		@PathVariable("productId") Integer productId, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return "product/" + productId + "/add_version";
    	}
    	else {
    		version.setProductId(productId);
	    	versionRepository.save(version);
	        return "redirect:/version/" + version.getId();
    	}
    }
	
	@GetMapping("/version/{id}") //Show a version
	public String showVersion(@PathVariable("id") Integer id, Model model) {
		Version version = versionRepository.findById(id).get();
		Delta delta = new Delta();
		Iterable<Delta> versionDeltas = deltaRepository.findByVersionId(id);
		Iterable<Version> productVersions = versionRepository.findByProductId(version.getProductId());
		model.addAttribute("version", version);
		model.addAttribute("versions", productVersions);
		model.addAttribute("versionDeltas", versionDeltas);
		model.addAttribute("delta", delta);
		return "version";
	}
	
	@PostMapping("/version/{id}/delta_result") //Create a version's delta
    public String deltaSubmit(@Valid @ModelAttribute Delta delta, 
		@PathVariable("id") Integer targetVersionId) {
		delta.setVersionId(targetVersionId);
		delta.setStatus("00");
    	deltaRepository.save(delta);
        return "redirect:/version/" + delta.getVersionId();
    }
	
	@PostMapping("delta/{id}/upload_file") //Upload a delta package
    public String upload_delta_file(@PathVariable("id") Integer id, 
    		@RequestParam("file")  MultipartFile file, HttpServletRequest request, Model model) {
		String contentType = file.getContentType();
		String fileName = file.getOriginalFilename();
		log.info("fileName = " + fileName);
		log.info("getContentType = " + contentType);
		String filePath = request.getSession().getServletContext().getRealPath("uploa_file/");
		String fileMd5sum = "";
		long fileSize = 0;
		
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			log.info("file save success !");
	        
			fileSize = dest.length();
			fileMd5sum = Md5hash.getMD5Hash(dest);
			
			
		} catch (IllegalStateException e) {
			log.info("file save fail : IllegalStateException");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("file save fail : IOException");
			e.printStackTrace();
		}
		
		Delta delta = deltaRepository.findById(id).get();
		delta.setFilePath(filePath + fileName);
		delta.setStatus("01");
		delta.setFileSize(fileSize);
		delta.setMd5Sum(fileMd5sum);
		deltaRepository.save(delta);
        return "redirect:/version/" + delta.getVersionId();
    }
	
}
