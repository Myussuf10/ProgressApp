import React, { useContext, useState } from 'react';
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
import SelectDropdown from 'react-native-select-dropdown';
import Dropdown from './Forms/Dropdown';
import CheckBox from '@react-native-community/checkbox'
import AttendanceParent from './AttendanceParent';
import Progress from './Progress';
import { AuthContext } from './store/AuthContext';
import CommentParent from './CommentParent';


const Subject = ({ navigation }) => {
  const authCtx = useContext(AuthContext)

  return (
    <View style={{ backgroundColor: '#B4B4B4', flex: 1 }}>
      <View style={styles.row}>
        <TouchableOpacity title="New Subject" onPress={() => { navigation.navigate('SubjectPage') }} style={styles.button}>
          <Text style={styles.txt}> Time Table </Text>
        </TouchableOpacity>
        <TouchableOpacity title="Attendance" onPress={() => { navigation.navigate('AttendanceParent') }} style={styles.button}>
          <Text style={styles.txt}> Attendance</Text>
        </TouchableOpacity>
        <TouchableOpacity title="New Subject" onPress={() => { navigation.navigate('Progress') }} style={styles.button}>
          <Text style={styles.txt}> Progress</Text>
        </TouchableOpacity>
        <TouchableOpacity title="New Subject" onPress={() => { navigation.navigate('CommentParent') }} style={styles.button}>
          <Text style={styles.txt}> Comments </Text>
        </TouchableOpacity>
      </View>

    </View>

  );
};

export default Subject;

const styles = StyleSheet.create({
  box: {
    marginLeft: 60
  },
  txt: {
    textAlign: 'center',
    fontSize: 20,
    color: "white",
  },
  row: {
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 100

  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    height: 50,
    margin: 5,
    alignContent: 'center'
  },

});