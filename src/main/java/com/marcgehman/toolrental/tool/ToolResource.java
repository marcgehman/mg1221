package com.marcgehman.toolrental.tool;

import java.util.List;

import com.marcgehman.toolrental.tool.ToolMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tools")
public class ToolResource {
    
    private final ToolMapper toolMapper;

    ToolResource(ToolMapper toolMapper) {
        this.toolMapper = toolMapper;
    }


    /**
     * List all of the tools
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity all() {
        return ResponseEntity
            .ok()
            .body(toolMapper.findAll());
    }

    /**
     * Add a new tool to the database
     * @param tool
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTool(@RequestBody Tool tool) {
        // Tool code must be unique.
        // First check to make sure the tool code does not yet exist.
        // If tool code exists, return 0, indicating failure. Else, insert the new tool into the database.
        if (toolMapper.findById(tool.code) != null) {
            return ResponseEntity
                .badRequest()
                .body(0);
        } else {
          Integer result = toolMapper.insert(tool);  
          return ResponseEntity
                .ok()
                .body(result);
        } 
    }


    /**
     * Delete a tool from the database
     * @param code
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteTool(@RequestParam("code") String code) {
        if (toolMapper.findById(code) != null) {
            Integer result = toolMapper.deleteById(code);
            return ResponseEntity
                .ok()
                .body(result);
        } else {
            return ResponseEntity
                .badRequest()
                .body(0);
        }
    }

    
}
