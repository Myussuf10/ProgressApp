import Admin from "../components/Admin";
import {create} from 'react-test-renderer'

const navigation = {
    navigate: jest.fn(),
}

const tree = create(<Admin navigation={navigation} />);


test('navigate to SignUp screen', ()=>{
    const button = tree.root.findByProps({testId: 'Button'}).props;
    button.onPress();

    expect(navigation.navigate).toBeCalledWith('SignUp');
    
})