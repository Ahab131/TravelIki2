package controller;

import service.PenggunaService;

//controller berfungsi untuk menghubungkan antara view dan service
//controller berfungsi untuk menerima input dari view dan mengirimkan output ke view
//controller berfungsi untuk memisahkan logika presentasi dari logika bisnis
//controller berfungsi untuk mengatur alur aplikasi dan menghubungkan antara view dan service

public class PenggunaController {
    private final PenggunaService penggunaService;

    public PenggunaController(PenggunaService penggunaService) {
        this.penggunaService = penggunaService;
    }

    // data diambil dari view kemudian dikirimkan ke service

}