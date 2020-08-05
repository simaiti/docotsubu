package model;

import java.util.List;

import dao.MutterDAO;

public class GetMutterListLogic {
	public List<Mutter> execute(){
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		return mutterList;
	}

	public boolean removeMutter(String id) {
		MutterDAO dao = new MutterDAO();
		System.out.println(id + "削除します");

		return dao.remove(id);
	}
}
