package jp.spring.artisans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
//「Serializable」オブジェクトを「バイト列」にして保存・送信
@Data
public class ArtisansSession implements Serializable{
//シリアライズ（保存）したデータと、後からデシリアライズ（読み込み）するクラスの互換性を確認するための識別子
//	1Lはバージョンの指定
	private static final long serialVersionUID = 1L;
	
	private List<String> memos = new ArrayList<String>();
}
