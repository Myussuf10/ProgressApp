import React, { useState } from 'react';
import {
  KeyboardAvoidingView,
  StyleSheet,
  Text,
  View,
  Button,
  TextInput,
  TouchableOpacity
} from 'react-native';
import axios from 'axios';

import { useEffect } from 'react';
import { fetchParents, fetchSubjects } from './util/http';
import SelectDropdown from 'react-native-select-dropdown';
import Dropdown from './Forms/Dropdown';
import  CheckBox  from '@react-native-community/checkbox'


const Subject = ({setSub, label}) => {
  const [state, setState] = useState({Maths:false, Science:false, English:false});
  const [Maths, setMaths] = useState(false)
  const [English, setEnglish] = useState(false)
  const [Science, setScience] = useState(false)


  const inputHandler=(newValue, target)=>{
    setState((currentInput)=>{
      return{
        ...currentInput,
        [target]:newValue
      };
      
    })
    setSub(...state);
  }

 

  return (
    // <ListItem>
    // <Avatar rounded 
    // source={{
    //     uri: 
    //     "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
    // </ListItem>
    // Invoking get method to perform a GET request

    <View style={{alignContent:"center"}}>

    

       
        {/* <CheckBox style={styles.box} value={state.label} onValueChange={(value) => {setState({...state, [label]:value}, setSub({...state}), ), console.log(state); } }/><Text style={styles.box}>{label}</Text> */}
        <CheckBox style={styles.box} 
        value={state} 
        onValueChange={null} />
        <Text style={styles.box}>{label}</Text>

        


    </View>
  );
};

export default Subject;

const styles = StyleSheet.create({
  box:{
    marginLeft:60
  },
  txt:{
    color:'black'
  },
  
});