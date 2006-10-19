package examples.flex2.add.service.impl;

import org.seasar.flex2.rpc.remoting.service.annotation.RemotingService;
import org.seasar.flex2.util.data.storage.StorageType;
import org.seasar.flex2.util.data.transfer.annotation.Import;
import org.seasar.flex2.util.data.transfer.annotation.Export;
import examples.flex2.add.dto.AddDto;
import examples.flex2.add.service.AddService;


@RemotingService
public class AddServiceImpl implements AddService {

	private AddDto addDto;
	
    public int calculate(int arg1, int arg2) {
        return arg1 + arg2;
    }

    public AddDto calculate2(AddDto addDto) {

        addDto.setSum(addDto.getArg1() + addDto.getArg2());
        this.addDto=addDto;
        return addDto;
    }
    
    public AddDto getAddDtoData(){
    	if(this.addDto != null){
    		return this.addDto;
    	}
    	this.addDto = new AddDto();
    	return this.addDto;
    }

    @Export(storage = StorageType.SESSION)
	public AddDto getAddDto() {
		return addDto;
	}

    @Import(storage = StorageType.SESSION)
	public void setAddDto(AddDto addDto) {
		this.addDto = addDto;
	}
}
