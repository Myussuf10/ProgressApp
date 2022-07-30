import { View, Text, KeyboardAvoidingView, StyleSheet, TouchableOpacity } from 'react-native'
import React from 'react'
import {Avatar} from '@rneui/themed';
import { ListItem } from '@rneui/base';

const Admin = ({navigation}) => {
  return (
    <KeyboardAvoidingView style={{screen:{flex:1}}}>
           <View style={styles.background}>
     <Avatar rounded 
     source={{
         uri: 
         "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",}}/>
      <Text>Admin</Text>
      </View>
      <View style={styles.row}>
      <TouchableOpacity title="LogIn" onPress={null} style={styles.button}>
          <Text style={styles.txt}> Students </Text>
      </TouchableOpacity>
      <TouchableOpacity title="LogIn" onPress={null} style={styles.button}>
          <Text style={styles.txt}> Teachers </Text>
      </TouchableOpacity>
      </View>
      <View style={styles.row}>
      <TouchableOpacity title="LogIn" onPress={null} style={styles.button}>
          <Text style={styles.txt}> Subjects </Text>
      </TouchableOpacity>
      <TouchableOpacity title="LogIn" onPress={null} style={styles.button}>
          <Text style={styles.txt}> Classes </Text>
      </TouchableOpacity>
      </View>
      <View style={styles.row}>
      <TouchableOpacity title="LogIn" onPress={null} style={styles.button}>
          <Text style={styles.txt}> Login </Text>
      </TouchableOpacity>
      <TouchableOpacity title="LogIn" onPress={null} style={styles.button}>
          <Text style={styles.txt}> Login </Text>
      </TouchableOpacity>
      </View>
    </KeyboardAvoidingView>
  )
}

export default Admin

const styles = StyleSheet.create({
    row:{
        marginLeft:30,
        flexDirection: 'row'
    },
   
    background: {
      alignItems: 'center',
      flex: 1,
      justifyContent: 'center',
      backgroundColor: '#B4B4B4',
      padding: 25,
      marginBottom: 130

    },
    txt: {
        textAlign: 'center',
        fontSize: 20,
        color: "white",
      },button: {
        backgroundColor: '#608d56',
        borderRadius: 5,
        padding: 10,
        width: 150,
        height: 60,
        margin: 5
      },
  });
  