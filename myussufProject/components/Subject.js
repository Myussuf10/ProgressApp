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
import CheckBox from '@react-native-community/checkbox'


const Subject = ({ setMaths, label }) => {
  const [state, setState] = useState({ Maths: false, Science: false, English: false });
  const [subjects, setSubjects] = useState()
  const [English, setEnglish] = useState(false)
  const [Science, setScience] = useState(false)


  useEffect(() => {
    async function getSubjects() {
      const subjects = await fetchSubjects();
      setSubjects(subjects)
    }
    getSubjects();
    console.log(subjects)
  })
  const inputHandler = (newValue, target) => {
    setState((currentInput) => {
      return {
        ...currentInput,
        [target]: newValue
      };

    })
    setSub(...state);
  }



  return (

    <View style={{ alignContent: "center" }}>




      {/* <CheckBox style={styles.box} value={state.label} 
        onValueChange={(value) => 
        {console.log(value),setState({...state, [label]:value}, setSub({...state}), ), console.log(state); } }/>
        <Text style={styles.box}>{label}</Text> */}

      {/* <CheckBox style={styles.box} value={Maths} 
        onValueChange={(value) => 
        {setMath( value),console.log(Maths); } }/>
        <Text style={styles.box}>Maths</Text> */}



    </View>
  );
};

export default Subject;

const styles = StyleSheet.create({
  box: {
    marginLeft: 60
  },
  txt: {
    color: 'black'
  },

});