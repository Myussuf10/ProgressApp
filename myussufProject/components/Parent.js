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
import { fetchParents } from './util/http';
import SelectDropdown from 'react-native-select-dropdown';
import Dropdown from './Forms/Dropdown';


const Parent = ({setParent}) => {
  const [state, setState] = useState({});
  const parents = []


  useEffect(()=>{
    async function getParents(){
     const parents = await fetchParents();
      setState(parents)
    }
    getParents();

  }, [])
  for(var i in state){
    parents.push(state[i].name)
  }
  
  
  return (
    // <ListItem>
    // <Avatar rounded 
    // source={{
    //     uri: 
    //     "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
    // </ListItem>
    // Invoking get method to perform a GET request

    <View style={styles.inputContainer}>
    <SelectDropdown style={styles.box} data={parents} defaultButtonText={"Select Parent"} onSelect={(x)=>{setParent(state.find((i)=>i.name===x).id)}} search={true}/>

    </View>
  );
};

export default Parent;

const styles = StyleSheet.create({
  inputContainer: {
    marginHorizontal: 4,
  
},
  box:{
    padding: 60
    
  },
  txt:{
    color:'black'
  },

  
});