package com.jayzebra.usermodule.adapter.in;

import com.jayzebra.usermodule.domain.port.in.UserAssetUsecase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/assets")
public class UserAssetController {
    private final UserAssetUsecase userAssetUsecase;

    public UserAssetController(UserAssetUsecase userAssetUsecase) {
        this.userAssetUsecase = userAssetUsecase;
    }

    @PutMapping("/{userId}/image")
    public void uploadUserImage(@PathVariable String userId, @RequestParam("file") MultipartFile file) throws Exception {
        userAssetUsecase.uploadUserImage(userId, file.getBytes());
    }
}
