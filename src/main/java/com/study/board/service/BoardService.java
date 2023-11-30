package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void boardWrite(Board board, MultipartFile file) throws Exception {

//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        String projectPath = "C:\\Spring\\img\\";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath(projectPath + fileName);

        boardRepository.save(board);

    }

    public List<Board> boardList(){

        return boardRepository.findAll();

    }

    //특정 게시물 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    //특정 게시물 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
}
