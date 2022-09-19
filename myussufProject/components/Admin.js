import { View, Text, KeyboardAvoidingView, StyleSheet, TouchableOpacity } from 'react-native'
import React from 'react'
import {Avatar} from '@rneui/themed';
import { ListItem } from '@rneui/base';
import AddTeacher from './AddTeacher';
import { Header } from '@react-navigation/stack';
import SignUp from './Forms/SignUp';
import SetUpClasses from './SetUpClasses';
import NewSubject from './NewSubject';
import AddAdmin from './AddAdmin';

const Admin = ({navigation}) => {
  return (
    <KeyboardAvoidingView
  keyboardVerticalOffset = {Header.HEIGHT} // adjust the value here if you need more padding
  style = {{ flex: 1, backgroundColor: '#B4B4B4' }}
  behavior = "padding" >
           <View style={styles.background} >
     <Avatar rounded 
     source={{
         uri: 
         "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
      <Text>Admin</Text>
      </View>
      <View style={styles.row}>
      <TouchableOpacity title="SignUp" onPress={()=>{navigation.navigate(SignUp)}} style={styles.button}>
          <Text style={styles.txt}> Add Students </Text>
      </TouchableOpacity>
      <TouchableOpacity title="AddTeacher" onPress={()=>{navigation.navigate('AddTeacher')}} style={styles.button}>
          <Text style={styles.txt}> Add Teachers </Text>
      </TouchableOpacity>
      </View>
      <View style={styles.row}>
      <TouchableOpacity title="Classes" onPress={()=>{navigation.navigate(SetUpClasses)}} style={styles.button}>
          <Text style={styles.txt}> Add Classes </Text>
      </TouchableOpacity>
      
      <TouchableOpacity title="New Subject" onPress={()=>{navigation.navigate('NewSubject')}} style={styles.button}>
          <Text style={styles.txt}> Add Subjects </Text>
      </TouchableOpacity>
      <View style={styles.row2}>
      <TouchableOpacity title="AddAdmin" onPress={()=>{navigation.navigate('AddAdmin')}} style={styles.button}>
          <Text style={styles.txt}> Add Admin </Text>
      </TouchableOpacity>
      </View>
     
      </View>
      
    </KeyboardAvoidingView>
  )
}

export default Admin

const styles = StyleSheet.create({
    row:{
      alignItems: 'center',
      justifyContent: 'center',  
      flexDirection: 'row',
      marginTop: 100
      
    },
     
    background: {
      alignItems: 'center',
      justifyContent: 'center',  
      marginTop: 5
    
    },
    txt: {
        textAlign: 'center',
        fontSize: 20,
        color: "white",
      },button: {
        backgroundColor: '#608d56',
        borderRadius: 5,
        padding: 5,
        height: 60,
        margin: 3,
        alignItems:'center'
      },
  });
  