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
import { CheckBox } from '@rneui/themed'



const Subject = () => {
  const [state, setState] = useState([]);


  useEffect(()=>{
    async function getSubjects(){
     const subjects = await fetchSubjects();
      setState(subjects)
    }
    getSubjects();
  }, [])


  return (
    // <ListItem>
    // <Avatar rounded 
    // source={{
    //     uri: 
    //     "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
    // </ListItem>
    // Invoking get method to perform a GET request

    <View>
        {/* {state.map((value,index)=>{
            return <CheckBox key={index} value={Object.values(value).subjectname} />
        })} */}

           {/* <Dropdown props = {state} label= "Subject"/> */}

    {/* <Button title='GetState' onPress={fetchParents}/>
          <Text style={styles.txt}>{state}</Text> */}
    </View>
  );
};

export default Subject;

const styles = StyleSheet.create({
  txt:{
    color:'black'
  },

  
});