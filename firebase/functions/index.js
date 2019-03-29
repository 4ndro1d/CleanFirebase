const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp();
const firestore = admin.firestore();

exports.registerUser = functions.auth.user().onCreate((user) => {
 const userId = user.uid
 firestore.doc(`users/${userId}`).set({
     "id" : userId,
     "email" : user.email
     })
});

exports.deleteList = functions.firestore.document('lists/{id}').onDelete((snap, context) => {
    var query = firestore.collection('items').where('listId','==', snap.data().id);
    query.get().then(function(querySnapshot) {
      querySnapshot.forEach(function(doc) {
        doc.ref.delete();
      });
    });
});