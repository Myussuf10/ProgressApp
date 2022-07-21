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
import {Avatar} from '@rneui/themed';
import { ListItem } from '@rneui/base';
import { useEffect } from 'react';
import { fetchParents, fetchSubjects } from './util/http';
import SelectDropdown from 'react-native-select-dropdown';
import Dropdown from './Forms/Dropdown';
import  CheckBox  from '@react-native-community/checkbox'


const Subject = ({setSub}) => {
  const [state, setState] = useState({Maths:false, English:false, Science:false});


  // useEffect(()=>{
  //   async function getSubjects(){
  //    const subjects = await fetchSubjects();
  //     setState(subjects)
  //   }
           
  //   getSubjects();
  // }, [])

 

  return (
    // <ListItem>
    // <Avatar rounded 
    // source={{
    //     uri: 
    //     "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
    // </ListItem>
    // Invoking get method to perform a GET request

    <View >

       
        <CheckBox value={state.Maths} onValueChange={(value) => { setSub({...state, Maths:value}), setState({...state, Maths:value}), console.log(state); } }/><Text style={{}}>Maths</Text>
        {/* <CheckBox value={state.English} onValueChange={(value) => { setSub({...state, English:value}), setState({...state, English:value}), console.log(state); } }/><Text>English</Text>
        <CheckBox value={state.Science} onValueChange={(value) => { setSub({...state, Science:value}), setState({...state, Science:value}), console.log(state); } }/><Text>Science</Text> */}


    </View>
  );
};

export default Subject;

const styles = StyleSheet.create({
  txt:{
    color:'black'
  },
  
});