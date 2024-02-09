package br.com.erudio.apiresterudio.controllers;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.services.BookService;
import br.com.erudio.apiresterudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
public class BookControler {

    @Autowired
    private BookService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all Books", description = "Find all Books", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookVo.class)))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
    )
    public ResponseEntity<List<BookVo>> findAll(){return ResponseEntity.ok().body(service.findAll());}

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find a Book", description = "Find a Book", tags = {"Book"},
        responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVo.class))),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<BookVo> findById(@PathVariable(name = "id") Integer id ){ return ResponseEntity.ok().body(service.findById(id)); }
}
